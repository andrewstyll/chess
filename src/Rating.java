package src;
import java.util.*;
import pieceDef.*;

public class Rating {

    //credit to chessprogramming.wikispaces.com/Simplified+evaluation+function for the rating board. And pretty much
    //everything else in the whole project

    //for each peice, see if it can be captured
    public static int ratePieceSafeness(boolean whitesMove) {
        //I need a board of all unsafe areas on the chessboard. To do this i need to know who's turn it is
        HashMap<Character, Piece> pieces = Board.getPieces();
        long wUnsafeBoard = 0L, bUnsafeBoard = 0L; //wCaptureBoard = where white pieces are in danger, bCaptureBoard = where black pieces are in danger
        int pointCount = 0;

        wUnsafeBoard = Moves.getUnsafeBoard(0, true);
        bUnsafeBoard = Moves.getUnsafeBoard(0, false);

        for(Map.Entry<Character, Piece> entry : pieces.entrySet()) {
            char key = entry.getKey();
            Piece chessPiece = entry.getValue();
           
            if(whitesMove) {
                if(chessPiece.getSide() == Piece.Side.WHITE) {
                    if(( wUnsafeBoard & chessPiece.getLocation()) == 0) {
                        continue;            
                    } else {
                        switch(key) {
                            case 'P':
                                pointCount -= 100;        
                                break;
                            case 'R':
                                pointCount -= 500;       
                                break;
                            case 'K':
                                pointCount -= 300;       
                                break;
                            case 'B':
                                pointCount -= 300;       
                                break;
                            case 'Q':
                                pointCount -= 900;        
                                break;
                            case 'A':
                                pointCount -= 200;        
                                break;
                        } 
                    }
                }
            } else {
                if(chessPiece.getSide() == Piece.Side.BLACK) {
                    if(( bUnsafeBoard & chessPiece.getLocation()) == 0) {
                        continue;            
                    } else {
                        switch(key) {
                            case 'p':
                                pointCount -= 100;        
                                break;
                            case 'r':
                                pointCount -= 500;      
                                break;
                            case 'k':
                                pointCount -= 300;       
                                break;
                            case 'b':
                                pointCount -= 300;       
                                break;
                            case 'q':
                                pointCount -= 900;        
                                break;
                            case 'a':
                                pointCount -= 200;        
                                break;
                        } 
                    }
                }
            }
        }
        return pointCount/2;
    }

    //collects the material value of all of the pieces on the chessBoard
    public static int rateBoardValue() {
        HashMap<Character, Piece> pieces = Board.getPieces();
        int pieceCount, pointCount = 0;

        for(Map.Entry<Character, Piece> entry : pieces.entrySet()) {
            char key = entry.getKey();
            Piece chessPiece = entry.getValue();
            pieceCount = chessPiece.getPieceCount();
            
            //i can change how I want this to work per piece
            switch(key) {
                case 'P':
                    pointCount += pieceCount*100;
                    break;
                case 'R':
                    pointCount += pieceCount*500;
                    break;
                case 'K':
                    pointCount += pieceCount*300;
                    break;
                case 'B':
                    pointCount += (pieceCount == 2 ? pieceCount * 300 : pieceCount * 250); 
                    break;
                case 'Q':
                    pointCount += pieceCount*900;
                    break;
                case 'p':
                    pointCount -= pieceCount*100;
                    break;
                case 'r':
                    pointCount -= pieceCount*500;
                    break;
                case 'k':
                    pointCount -= pieceCount*300;
                    break;
                case 'b':
                    pointCount -= (pieceCount == 2 ? pieceCount * 300 : pieceCount * 250); 
                    break;
                case 'q':
                    pointCount -= pieceCount*900;
                    break;
            }
        }
        return pointCount;
    }
    
    public static int rateMoveOptions(int numMoves) {
        int pointCount = 0;
        pointCount += numMoves*5; //5 pointes per valid move
        if(numMoves == 0) { // if we're in check or somethign that's bad so minus a billion points
            pointCount += (-1)*Main.CHECK_MATE;
        }
        return pointCount;
    }
    
    public static int rateBoardPosition() {
        HashMap<Character, Piece> pieces = Board.getPieces();
        int pointCount = 0;
        int mPW = 0, mPB = 0;
        int kingPositionW = 0, kingPositionB = 0;
        boolean pRW = false, pRB = false, hasQueenW = false, hasQueenB = false;
        long board = 0L;

        for(Map.Entry<Character, Piece> entry : pieces.entrySet()) {
            char key = entry.getKey();
            Piece chessPiece = entry.getValue();
            
            board = chessPiece.getLocation();
            for(int i = 0; i < 64; i++) {
                if(((board>>i) & 1) == 1) {
                    switch(key) {
                        case 'P':
                            pRW = true;
                            pointCount += RatingBoard.pBoardW[i/8][i%8];
                            break;
                        case 'R':
                            pRW = true;
                            pointCount += RatingBoard.rBoardW[i/8][i%8];
                            break;
                        case 'K':
                            mPW++;
                            pointCount += RatingBoard.kBoardW[i/8][i%8];
                            break;
                        case 'B':
                            mPW++;
                            pointCount += RatingBoard.bBoardW[i/8][i%8];
                            break;
                        case 'Q':
                            hasQueenW = true;
                            pointCount += RatingBoard.qBoardW[i/8][i%8];
                            break;
                        case 'A':
                            kingPositionW = i;
                            break;
                        
                        case 'p':
                            pRB = true;
                            pointCount -= RatingBoard.pBoardW[7-(i/8)][7-(i%8)];
                            break;
                        case 'r':
                            pRB = true;
                            pointCount -= RatingBoard.rBoardW[7-(i/8)][7-(i%8)];
                            break;
                        case 'k':
                            mPB++;
                            pointCount -= RatingBoard.kBoardW[7-(i/8)][7-(i%8)];
                            break;
                        case 'b':
                            mPB++;
                            pointCount -= RatingBoard.bBoardW[7-(i/8)][7-(i%8)];
                            break;
                        case 'q':
                            hasQueenB = true;
                            pointCount -= RatingBoard.qBoardW[7-(i/8)][7-(i%8)];
                            break;
                        case 'a':
                            kingPositionB = i;
                            break;
                    }
                }
            }
        }
        
        //Both sides have no queens or
        //Every side which has a queen has additionally no other pieces or one minorpiece
        if( ((hasQueenW && (!pRW && mPW < 2)) || (hasQueenB && (!pRB && mPB > 1))) || (!hasQueenW && !hasQueenB) ) {
            //END BOARD
            pointCount += RatingBoard.aEndBoardW[kingPositionW/8][kingPositionW%8]; //White
            pointCount -= RatingBoard.aEndBoardW[7-(kingPositionB/8)][7-(kingPositionB%8)];//Black
        } else {
            //MID BOARD
            pointCount += RatingBoard.aMidBoardW[kingPositionW/8][kingPositionW%8];
            pointCount -= RatingBoard.aMidBoardW[7-(kingPositionB/8)][7-(kingPositionB%8)];
        }
        return pointCount;
    }
    
    public static int evaluate(int[] moves, boolean whitesMove, int depth) {
        int pointCount = 0;

        //Sum my stuff and subtract the other guys stuff somehow
        pointCount += rateBoardPosition();
        pointCount += ratePieceSafeness(whitesMove);
        pointCount += rateMoveOptions(Moves.getNumMoves(moves)); //pass in the number of moves in here
        pointCount += rateBoardValue();

        //System.out.println(pointCount);

        return whitesMove == true ? pointCount+(50*depth):(-1)*pointCount+(50*depth);
    }
}
