package src;
import java.util.*;

class AlphaBeta {
     
    static int MAX_DEPTH = 4;
  
    //beta = must be at least this good, alpha means too good. Use hard cut
    static int[] maxAlphaBeta(int alpha, int beta, int depth, boolean whitesMove, int move) {
        int[] MAS = new int[2];
        int score;
        int[] moves = new int[Moves.MAX_MOVES];   

        if(whitesMove) { //if it's whites turn get white moves
            moves = Moves.possibleMovesWhite(whitesMove);    
        } else {
            moves = Moves.possibleMovesBlack(whitesMove);    
        }
        
        if(depth == MAX_DEPTH) {
            MAS = Main.encodeMAS(move, Rating.evaluate(moves, whitesMove, depth));
            return MAS;
            //return Rating.evaluate(moves, whitesMove, depth);
        }

        //we've hit a checkmate
        /*if(moves[0] == 0) {
            //that means no moves were ever initialised so this must be a checkmate
            return whitesMove == true ? Main.CHECK_MATE : (-1)*Main.CHECK_MATE;
        }*/

        for(int i = 0; i < Moves.MAX_MOVES; i++) {
            if(moves[i] != 0) {
                
                Moves.makeMoveAllPieces(moves[i], false); // I have to make a move in order to get the score 
                MAS = minAlphaBeta(alpha, beta, depth+1, !whitesMove, moves[i]);
                score = Main.decodeMASScore(MAS);
                Moves.undoMoveAllPieces();
                
                if(score >= beta) {
                    MAS = Main.encodeMAS(moves[i], beta);
                    return MAS;
                    //return beta;
                }
                if(score > alpha) {
                    alpha = score;
                    MAS = Main.encodeMAS(moves[i], alpha);
                }
            } else {
                break;
            }
        }
        //MAS has already stored the alpha above in the for loop along with its corresponding move
        return MAS;
        //return alpha;
    }

    static int[] minAlphaBeta(int alpha, int beta, int depth, boolean whitesMove, int move) {
        int[] MAS = new int[2];
        int score;
        int[] moves = new int[Moves.MAX_MOVES];   
        
        if(whitesMove) { //if it's whites turn get white moves
            moves = Moves.possibleMovesWhite(whitesMove);    
        } else {
            moves = Moves.possibleMovesBlack(whitesMove);    
        }
        
        if(depth == MAX_DEPTH) {
            MAS = Main.encodeMAS(move, Rating.evaluate(moves, whitesMove, depth));
            return MAS;
            //return Rating.evaluate(moves, whitesMove, depth);
        }

        //we've hit a checkmate
        /*if(moves[0] == 0) {
            //that means no moves were ever initialised so this must be a checkmate
            return whitesMove == true ? Main.CHECK_MATE : (-1)*Main.CHECK_MATE;
        }*/

        for(int i = 0; i < Moves.MAX_MOVES; i++) {
            if(moves[i] != 0) {
                
                Moves.makeMoveAllPieces(moves[i], false); // I have to make a move in order to get the score
                MAS = maxAlphaBeta(alpha, beta, depth+1, !whitesMove, moves[i]);
                score = Main.decodeMASScore(MAS);
                Moves.undoMoveAllPieces();
                
                if(score <= alpha) {
                    MAS = Main.encodeMAS(moves[i], alpha);
                    return MAS;
                    //return alpha;
                }
                if(score < beta) {
                    beta = score;
                    MAS = Main.encodeMAS(moves[i], beta);
                }
            } else {
                break;
            }
        }
        //MAS has already stored the beta above in the for loop along with its corresponding move
        return MAS;
        //return beta;
    }

/**
 * Test code to verify alphabeta
 **/
 
 /*
    static Scanner reader = new Scanner(System.in);

    static int[] fillMoves(int n) {
        int[] tmp = new int[Moves.MAX_MOVES];
        for(int i = 0; i < n; i++) {
            tmp[i] = i+1;
        }
        return tmp;
    }
  
    //AT THE END OF THE DAY, EXITING THIS FUNCTION WILL RETURN THE FINAL VALUE. SCORE DETERMINES THE SCORE IN THE NODE
    static int maxAlphaBeta(int alpha, int beta, int depth, boolean whitesMove, int move) {
        int score, moveIndex; //this will be the score of the best move and the index of the best move in the moves array;
        int[] moves = new int[Moves.MAX_MOVES];   
        int n;

        //at a node, it will create a list of moves, and then iterate through those moves and return the score of that
        //node. The moves we are creating are the children of the current node
        if(depth == MAX_DEPTH) {
            //this is the score for the depth above because the node above will call this looking for its score 
            System.out.print("enter a score for depth " + (depth) +":");
            int s = reader.nextInt();
            return s;
        }
        
        System.out.print("enter number of children below Depth: " + depth +" Move: " + move + ": ");
        n = reader.nextInt();
        moves = fillMoves(n);
        
        Moves.showMoves(moves);

        for(int i = 0; i < n; i++) {
            System.out.println( "Depth: " + depth + " Node: " + moves[i]);
            //score of children;
            score = minAlphaBeta(alpha, beta, depth+1, !whitesMove, moves[i]);
            if(score >= beta) {
                return beta;
            }
            if(score > alpha) {
                alpha = score;
            }
        }
        return alpha;
    }

    static int minAlphaBeta(int alpha, int beta, int depth, boolean whitesMove, int move) {
        int score, moveIndex; //this will be the score of the best move and the index of the best move in the moves array;
        int[] moves = new int[Moves.MAX_MOVES];   
        int n;

        if(depth == MAX_DEPTH) {
            //this is the score for the depth above because the node above will call this looking for its score 
            System.out.print("enter a score for depth " + (depth) +":");
            int s = reader.nextInt();
            return s;
        }
        
        System.out.print("enter number of children below Depth: " + depth +" Move: " + move + ": ");
        n = reader.nextInt();
        moves = fillMoves(n);
        
        Moves.showMoves(moves);

        for(int i = 0; i < n; i++) {
            System.out.println( "Depth: " + depth + " Node: " + moves[i]);
            score = maxAlphaBeta(alpha, beta, depth+1, !whitesMove, moves[i]);
            if(score <= alpha) {
                return alpha;
            }
            if(score < beta) {
                beta = score;
            }
        }
        return beta;
    }*/
}
