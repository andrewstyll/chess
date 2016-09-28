import java.util.*;
import pieceDef.*;
// what is the purpose of the move class....
// the purpose of this class is to handle the movements of all of the pieces
// this class should get the moves available to all of the pieces, and compile them inside of the moves array.
// each move will be stored as an integer. starting(number between 0 and 64), ending(number between 0 and 64)
// captured and 

public class Moves {

    static int MAX_MOVES = 218; //this is supposed to be the maximum number of moves for any position
   
    public static void movesTest() {
        showMoves(possibleMovesWhite(true));
        showMoves(possibleMovesBlack(false));
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

    //makes a move based on encoded input
    public static long makeMove(int move, long location, int promo) {
        int startLocation = 0, endLocation = 0;
        long base = 0L, returnBoard = 0L; //with a return board, the move doesn't get saved unless i want it to

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
            returnBoard = location & ~base;
            base = Main.powerOf2(endLocation);
            returnBoard = location | base;
        } else { //remove deleted piece wherever it exists
            base = Main.powerOf2(endLocation);
            returnBoard = location & ~base;
        }
        
        return returnBoard;
    }
   
    //so every time a move is made I need a way of remembering the original location of the move so then when i
    //evntually complete my alpha beta function, the pieces on my board haven't actually gone anywhere. but i need to
    //store and access their "new" temporary locations. My idea is this:
    //
    //Each piece will have a stack that holds the temporary locations of the pieces. The top value of the stack will be
    //the "current" location of the piece. The maximum stack size is 10 so this should be okay. so every time i undo a
    //move, i'll just pop off the tops of every piece and the moves will be undone. 
    //
    //This is correct because every make move will push a new value on the stack, and every undo move will pop a value.
    //If every make move is matched by an undo move, the stack should return to 0 when I return to the head
 
    public static void makeMoveAllPieces(int move) {
        HashMap<Character, Piece> pieces = Board.getPieces();
        int promo;
        long newLocation, board = 0L;

        for(Map.Entry<Character, Piece> entry : pieces.entrySet()) {
            char key = entry.getKey();
            Piece chessPiece = entry.getValue();
            
            switch(key) {
                case 'R':
                    promo = 1;
                    break;
                case 'K':
                    promo = 2;
                    break;
                case 'B':
                    promo = 4;
                    break;
                case 'Q':
                    promo = 8;
                    break;
                case 'r':
                    promo = 16;
                    break;
                case 'k':
                    promo = 32;
                    break;
                case 'b':
                    promo = 64;
                    break;
                case 'q':
                    promo = 128;
                    break;
                default:
                    promo = 0;
                    break;
            }
            newLocation = makeMove(move, chessPiece.getLocation(), promo);
            chessPiece.pushMove(newLocation);
        
            //for display and test purposes
            board |= newLocation;
        }
    }
   
    //this should return all legal moves for white, AKA white should not put its king in check with any of these
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

    //captures only update when get move is called for each piece
    //this is a final captrue zone after all of the moves are made. This isn't useful for eliminating bad moves from the
    //board
    static long getBlackCaptures(HashMap<Character, Piece> pieces) {
        long gBC = (pieces.get('p').getPCaptures() | pieces.get('r').getPCaptures() | pieces.get('k').getPCaptures() | 
                    pieces.get('b').getPCaptures() | pieces.get('q').getPCaptures() | pieces.get('a').getPCaptures());
        return gBC;
    }
    
    static long getWhiteCaptures(HashMap<Character, Piece> pieces) {
        long gWC = (pieces.get('P').getPCaptures() | pieces.get('R').getPCaptures() | pieces.get('K').getPCaptures() | 
                    pieces.get('B').getPCaptures() | pieces.get('Q').getPCaptures() | pieces.get('A').getPCaptures());
        return gWC;
    }

    public static boolean kingSafety(int move, boolean whitesMove) {
        HashMap<Character, Piece> pieces = Board.getPieces();
        long unsafeBoard = 0L;

        Piece pawnW = pieces.get('P');
        Piece rookW = pieces.get('R');
        Piece knightW = pieces.get('K');
        Piece bishopW = pieces.get('B');
        Piece queenW = pieces.get('Q');
        Piece kingW = pieces.get('A');

        Piece pawnB = pieces.get('p');
        Piece rookB = pieces.get('r');
        Piece knightB = pieces.get('k');
        Piece bishopB = pieces.get('b');
        Piece queenB = pieces.get('q');
        Piece kingB = pieces.get('a');

        long PB = makeMove(move, pawnW.getLocation(), 0);
        long RB = makeMove(move, rookW.getLocation(), 1);
        long KB = makeMove(move, knightW.getLocation(), 2);
        long BB = makeMove(move, bishopW.getLocation(), 4);
        long QB = makeMove(move, queenW.getLocation(), 8);
        long AB = makeMove(move, kingW.getLocation(), 0);

        long pB = makeMove(move, pawnB.getLocation(), 0);
        long rB = makeMove(move, rookB.getLocation(), 16);
        long kB = makeMove(move, knightB.getLocation(), 32);
        long bB = makeMove(move, bishopB.getLocation(), 64);
        long qB = makeMove(move, queenB.getLocation(), 128);
        long aB = makeMove(move, kingB.getLocation(), 0);

        long wBoard = PB | RB | KB | BB | QB | AB;
        long bBoard = pB | rB | kB | bB | qB | aB;
        //this is now the location of all of the peices on the board. Now I need to check their capture zones
        //get all the potential moves for all of the black pieces.
        
        if(whitesMove) { //if it's white's move, get the black captures and AND them with the white king location
            unsafeBoard = unsafeBoard | pawnB.getMoveBoard(bBoard, wBoard, pB);

            for(long i = 0; i < 64; i++) {
                if(((rB>>i) & 1) == 1) {
                    unsafeBoard = unsafeBoard | rookB.getMoveBoard(bBoard, wBoard, i);
                }
                if(((bB>>i) & 1) == 1) {
                    unsafeBoard = unsafeBoard | bishopB.getMoveBoard(bBoard, wBoard, i);
                }
                if(((qB>>i) & 1) == 1) {
                    unsafeBoard = unsafeBoard | queenB.getMoveBoard(bBoard, wBoard, i);
                }
                if(((kB>>i) & 1) == 1) {
                    unsafeBoard = unsafeBoard | knightB.getMoveBoard(bBoard, wBoard, i);
                }
                if(((aB>>i) & 1) == 1) {
                    unsafeBoard = unsafeBoard | kingB.getMoveBoard(bBoard, wBoard, i);
                }
            }
            
            //if it's whites move, check the black pieces with whites board
            if((unsafeBoard & kingW.getLocation()) == 0) {
                return true;
            } else {
                return false;
            }
        } else {

            unsafeBoard = unsafeBoard | pawnW.getMoveBoard(bBoard, wBoard, PB);

            for(long i = 0; i < 64; i++) {
                if(((RB>>i) & 1) == 1) {
                    unsafeBoard = unsafeBoard | rookW.getMoveBoard(bBoard, wBoard, i);
                }
                if(((BB>>i) & 1) == 1) {
                    unsafeBoard = unsafeBoard | bishopW.getMoveBoard(bBoard, wBoard, i);
                }
                if(((QB>>i) & 1) == 1) {
                    unsafeBoard = unsafeBoard | queenW.getMoveBoard(bBoard, wBoard, i);
                }
                if(((KB>>i) & 1) == 1) {
                    unsafeBoard = unsafeBoard | knightW.getMoveBoard(bBoard, wBoard, i);
                }
                if(((AB>>i) & 1) == 1) {
                    unsafeBoard = unsafeBoard | kingW.getMoveBoard(bBoard, wBoard, i);
                }
            }
                
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

    public static void showMoves(int[] moves) {
        for(int i = 0; i < MAX_MOVES; i++) {
            if(moves[i] != 0) {
                System.out.print(Integer.toHexString(moves[i]) + " ");
            }
        }
        System.out.println("");
    }
}
