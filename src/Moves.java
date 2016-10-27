package src;

import java.util.*;
import pieceDef.*;
// what is the purpose of the move class....
// the purpose of this class is to handle the movements of all of the pieces
// this class should get the moves available to all of the pieces, and compile them inside of the moves array.
// each move will be stored as an integer. starting(number between 0 and 64), ending(number between 0 and 64)
// captured and 

public class Moves {

    public static int MAX_MOVES = 218; //this is supposed to be the maximum number of moves for any position

    public static boolean containsMove(int[] moves, int move) {
        for(int m : moves) {
            if(m == move) {
                return true;
            } else if(m == 0) {
                break;
            }
        }
        return false;
    }

    public static int[] encodeMAS(int move, int score) {
        int[] MAS = new int[2];
        MAS[0] = move;
        MAS[1] = score;
        return MAS;
    }

    public static int decodeMASMove(int[] MAS) {
        return MAS[0];
    }
   
    public static int decodeMASScore(int[] MAS) {
        return MAS[1];
    }
    
    public static int getNumMoves(int[] moves) {
        int numMoves = 0;
        for(int i = 0; i < MAX_MOVES; i++) {
            if(moves[i] == 0) {
                break;
            } else {
                numMoves++;
            }
        }
        return numMoves;
    }

    static int determinePromo(char key) {
        switch(key) {
            case 'R':
                return 1;
            case 'K':
                return 2;
            case 'B':
                return 4;
            case 'Q':
                return 8;
            case 'r':
                return 16;
            case 'k':
                return 32;
            case 'b':
                return 64;
            case 'q':
                return 128;
            default:
                return 0;
        }
    }

    public static void undoMoveAllPieces() {
        HashMap<Character, Piece> pieces = Board.getPieces();
        
        for(Map.Entry<Character, Piece> entry : pieces.entrySet()) {
            Piece chessPiece = entry.getValue();
            if(!chessPiece.moveHistoryEmpty()) {
                chessPiece.popMove();
            }
        }
    }
    
    public static int encodeMove(int y1, int x1, int y2, int x2, char promote) {
        int move = 0;
        move = (x1) | (y1<<4) | (x2<<8) | (y2<<12);
        switch(promote) {
            case 'R':
                move = move | 1<<16;
                break;
            case 'K':
                move = move | 1<<17;
                break;
            case 'B':
                move = move | 1<<18;
                break;
            case 'Q':
                move = move | 1<<19;
                break;
            case 'r':
                move = move | 1<<20;
                break;
            case 'k':
                move = move | 1<<21;
                break;
            case 'b':
                move = move | 1<<22;
                break;
            case 'q':
                move = move | 1<<23;
                break;
        }
        return move;
    }

    private static int decodeMove(int move, int step) {
        //use mask to mask off the first byte for the starting point
        int xMask, yMask, pMask, x, y, promote;

        if(step == 1) { //looking for x1 and y1
            xMask = 0x0000000F;
            yMask = 0x000000F0;
            x = move&xMask;
            y = ((move&yMask)>>4);
        } else if(step == 2) {
            xMask = 0x00000F00;
            yMask = 0x0000F000;
            x = ((move&xMask)>>8);
            y = ((move&yMask)>>12);
        } else { // step will equal 3
            pMask = 0x00FF0000;
            promote = ((move&pMask)>>16);
            return promote;
        }
        
        return (y*8+x);
    }

    //makes a move based on encoded input
    public static long makeMove(int move, long location, int promo) {
        int startLocation = 0, endLocation = 0;
        long base = 0L, returnBoard = 0L, newLocation = 0L; //with a return board, the move doesn't get saved unless i want it to

        if(decodeMove(move, 3) == 0) { //regular move
            startLocation = decodeMove(move, 1); //starting location on grid
            endLocation = decodeMove(move, 2);
            
        } else { //pawn promotion //not only must i know black and white, i must also know what board i am adding the promoted pawn to
            startLocation = decodeMove(move, 1); //starting location on grid
            endLocation = decodeMove(move, 2);
            int piecePromo = decodeMove(move, 3);

            if(piecePromo == promo) {
                base = Main.powerOf2(endLocation);
                returnBoard = location | base;
            }
        }
        
        if(((location>>startLocation)&1) == 1) { //remove piece existing on board (only true for correct board)
            base = Main.powerOf2(startLocation);
            newLocation = location & ~base; //return board = location minus the moved piece
            base = Main.powerOf2(endLocation); //the is the new location of the moved piece
            returnBoard = newLocation | base;
        } else { //remove deleted piece wherever it exists
            base = Main.powerOf2(endLocation);
            returnBoard = location & ~base;
        }
        
        return returnBoard;
    }
 
    public static void makeMoveAllPieces(int move, boolean updateLocation) {
        HashMap<Character, Piece> pieces = Board.getPieces();
        int promo;
        long newLocation, board = 0L;

        for(Map.Entry<Character, Piece> entry : pieces.entrySet()) {
            char key = entry.getKey();
            Piece chessPiece = entry.getValue();
            promo = determinePromo(key); 
            newLocation = makeMove(move, chessPiece.getLocation(), promo);
            
            if(!updateLocation) {
                chessPiece.pushMove(newLocation);
            } else {
                chessPiece.setLocation(newLocation);
            }
            //for display and test purposes
            board |= newLocation;
        }
        if(updateLocation) {
            Board.drawBoard(board);
        }
    }
   
    static long createUnsafeBoard(long location, long wBoard, long bBoard, Piece p) {
        long unsafeBoard = 0L;

        for(long i = 0; i < 64; i++) {
            if(((location>>i) & 1) == 1) {
                unsafeBoard = unsafeBoard | p.getMoveBoard(bBoard, wBoard, i);
            }
        }
        return unsafeBoard;
    }

    //upon being given a move and a turn, this function will make the move, then compile a board of all dangerous
    //squares given that first move. If no move is given, then the unsafe locations are given given 
    public static long getUnsafeBoard(int move, boolean whitesMove) {
        HashMap<Character, Piece> pieces = Board.getPieces();
        long unsafeBoard = 0L, tmpBoard = 0L;
        long wBoard = 0L, bBoard = 0L;

        //this part determines the new locations of each piece after a move and stores them all together
        for(Map.Entry<Character, Piece> entry : pieces.entrySet()) {
            char key = entry.getKey();
            Piece chessPiece = entry.getValue();

            if(move == 0) {
                tmpBoard = makeMove(move, chessPiece.getLocation(), determinePromo(key));
            } else {
                tmpBoard = chessPiece.getLocation();
            }
            if(chessPiece.getSide() == Piece.Side.WHITE) {
                wBoard |= tmpBoard;
            } else {
                bBoard |= tmpBoard;
            }
        }

        for(Map.Entry<Character, Piece> entry : pieces.entrySet()) {
            char key = entry.getKey();
            Piece chessPiece = entry.getValue();
            
            if(move == 0) {
                tmpBoard = makeMove(move, chessPiece.getLocation(), determinePromo(key));
            } else {
                tmpBoard = chessPiece.getLocation();
            }
            if(whitesMove) { //If it's whites move, get the black moves as the unsafe board
                if(chessPiece.getSide() == Piece.Side.BLACK) {
                    if(key == 'p' || key == 'P' ) {
                        unsafeBoard |= chessPiece.getMoveBoard(bBoard, wBoard, tmpBoard);
                    } else {
                        unsafeBoard |= createUnsafeBoard(tmpBoard, wBoard, bBoard, chessPiece); 
                    }
                }
            } else { // if it's blacks move, get the white moves as the unsafe board
                if(chessPiece.getSide() == Piece.Side.WHITE) {
                    if(key == 'p'|| key == 'P' ) {
                        unsafeBoard |= chessPiece.getMoveBoard(bBoard, wBoard, tmpBoard);
                    } else {
                        unsafeBoard |= createUnsafeBoard(tmpBoard, wBoard, bBoard, chessPiece);
                    }
                }
            }
        }
        return unsafeBoard;
    }

    //Given a move and who's turn it is, this function will compare the postions of the kings to a board that contains
    //all the opposing players capture zone squares. If the king is safe, it will return true, if it isn't it'll return
    //false.
    public static boolean kingSafety(int move, boolean whitesMove) {
        HashMap<Character, Piece> pieces = Board.getPieces();
        long unsafeBoard = getUnsafeBoard(move, whitesMove);
        Piece kingW = pieces.get('A');
        Piece kingB = pieces.get('a');
        if(whitesMove) {
            //if it's whites move, check the black pieces with whites board
            if((unsafeBoard & kingW.getLocation()) == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if((unsafeBoard & kingB.getLocation()) == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    //only add to the array if the move will be safe for each colour's king
    private static int[] compileMoves(int[] p, int[] r, int[] k, int[] b, int[] q, int[] a, boolean whitesMove) {
        
        int[] moves = new int[MAX_MOVES];
        int pI = 0, rI = 0, kI = 0, bI = 0, qI = 0, aI = 0;
        
        for(int i = 0; i < MAX_MOVES; i++) {
            if(moves[i] != 0) {
                continue;
            }
            if (p[pI] != 0 && pI < MAX_MOVES) {
                if(kingSafety(p[pI], whitesMove)) {
                    moves[i] = p[pI++];
                } else {
                    pI++; //we want to skip this move cause it isn't valid
                    i--;
                }
            } else if (r[rI] != 0 && rI < MAX_MOVES) {
                if(kingSafety(r[rI], whitesMove)) {
                    moves[i] = r[rI++];
                } else {
                    rI++; //we want to skip this move cause it isn't valid
                    i--;
                }
            } else if (k[kI] != 0  && kI < MAX_MOVES) {
                if(kingSafety(k[kI], whitesMove)) {
                    moves[i] = k[kI++];
                } else {
                    kI++; //we want to skip this move cause it isn't valid
                    i--;
                }
            } else if (b[bI] != 0 && bI < MAX_MOVES) {
                if(kingSafety(b[bI], whitesMove)) {
                    moves[i] = b[bI++];
                } else {
                    bI++; //we want to skip this move cause it isn't valid
                    i--;
                }
            } else if (q[qI] != 0 && qI < MAX_MOVES) {
                if(kingSafety(q[qI], whitesMove)) {
                    moves[i] = q[qI++];
                } else {
                    qI++; //we want to skip this move cause it isn't valid
                    i--;
                }
            } else if (a[aI] != 0 && aI < MAX_MOVES) {
                if(kingSafety(a[aI], whitesMove)) {
                    moves[i] = a[aI++];
                } else {
                    aI++; //we want to skip this move cause it isn't valid
                    i--;
                }
            } else {
                break;
            }
        }
        return moves;
    }

    static long getBlackPosition(HashMap<Character, Piece> pieces) {
        long gBP = (pieces.get('p').getLocation() | pieces.get('r').getLocation() | pieces.get('k').getLocation() | 
                    pieces.get('b').getLocation() | pieces.get('q').getLocation() | pieces.get('a').getLocation());
        return gBP;
    }

    static long getWhitePosition(HashMap<Character, Piece> pieces) {
        long gWP = (pieces.get('P').getLocation() | pieces.get('R').getLocation() | pieces.get('K').getLocation() | 
                    pieces.get('B').getLocation() | pieces.get('Q').getLocation() | pieces.get('A').getLocation());
        return gWP;
    }

    public static int[] possibleMovesWhite(boolean whitesMove) {

        HashMap<Character, Piece> pieces = Board.getPieces();

        int[] pawnMoves = new int[MAX_MOVES];
        int[] rookMoves = new int[MAX_MOVES];
        int[] knightMoves = new int[MAX_MOVES];
        int[] bishopMoves = new int[MAX_MOVES];
        int[] queenMoves = new int[MAX_MOVES];
        int[] kingMoves = new int[MAX_MOVES];

        long piecesB = getBlackPosition(pieces);
        long piecesW = getWhitePosition(pieces);
        
        pawnMoves = pieces.get('P').getMoves(piecesB, piecesW);
        rookMoves = pieces.get('R').getMoves(piecesB, piecesW);
        knightMoves = pieces.get('K').getMoves(piecesB, piecesW);
        bishopMoves = pieces.get('B').getMoves(piecesB, piecesW);
        queenMoves = pieces.get('Q').getMoves(piecesB, piecesW);
        kingMoves = pieces.get('A').getMoves(piecesB, piecesW);
    
        return compileMoves(pawnMoves, rookMoves, knightMoves, bishopMoves, queenMoves, kingMoves, whitesMove);
    }
    
    public static int[] possibleMovesBlack(boolean whitesMove) {
        //whitesMove will always be false here won't it LOL

        HashMap<Character, Piece> pieces = Board.getPieces();
        
        int[] pawnMoves = new int[MAX_MOVES];
        int[] rookMoves = new int[MAX_MOVES];
        int[] knightMoves = new int[MAX_MOVES];
        int[] bishopMoves = new int[MAX_MOVES];
        int[] queenMoves = new int[MAX_MOVES];
        int[] kingMoves = new int[MAX_MOVES];
        
        long piecesB = getBlackPosition(pieces);
        long piecesW = getWhitePosition(pieces);
        
        pawnMoves = pieces.get('p').getMoves(piecesB, piecesW);
        rookMoves = pieces.get('r').getMoves(piecesB, piecesW);
        knightMoves = pieces.get('k').getMoves(piecesB, piecesW);
        bishopMoves = pieces.get('b').getMoves(piecesB, piecesW);
        queenMoves = pieces.get('q').getMoves(piecesB, piecesW);
        kingMoves = pieces.get('a').getMoves(piecesB, piecesW);

        return compileMoves(pawnMoves, rookMoves, knightMoves, bishopMoves, queenMoves, kingMoves, whitesMove);
    }
    
    public static void movesTest() {
        showMoves(possibleMovesWhite(true));
        showMoves(possibleMovesBlack(false));
    }

    public static void showMoves(int[] moves) {
        for(int i = 0; i < MAX_MOVES; i++) {
            if(moves[i] != 0) {
                System.out.print(Integer.toHexString(moves[i]) + " ");
            }
        }
        System.out.println("");
    }
}
