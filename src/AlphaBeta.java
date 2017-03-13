package src;
import java.util.*;

class AlphaBeta {
     
    static int MAX_DEPTH = 4;
  
    //beta = must be at least this good, alpha means too good. Use hard cut
    static int[] maxAlphaBeta(int alpha, int beta, int depth, boolean whitesMove, int move) {
        int[] MAS = new int[2];
        int score;
        int[] moves;   

        if(whitesMove) { //if it's whites turn get white moves
            moves = Moves.possibleMovesWhite(whitesMove);
        } else {
            moves = Moves.possibleMovesBlack(whitesMove);    
        }
        
        if(depth == MAX_DEPTH || moves.length == 0) {
            //System.out.println("rating: " + Rating.evaluate(moves, whitesMove, depth)+ " move: " + Integer.toHexString(move));
            MAS = Moves.encodeMAS(move, Rating.evaluate(moves, whitesMove, depth));
            return MAS;
        }

        for(int i = 0; i < moves.length; i++) {
            Moves.makeMoveAllPieces(moves[i], false); // I have to make a move in order to get the score 
            MAS = minAlphaBeta(alpha, beta, depth+1, !whitesMove, moves[i]);
            score = Moves.decodeMASScore(MAS);
            Moves.undoMoveAllPieces();
            
            if(score > alpha) {
                alpha = score;
                if(depth == 0) {
                    move = Moves.decodeMASMove(MAS);
                }
                MAS = Moves.encodeMAS(move, alpha);
            }
            if(alpha >= beta) {
                MAS = Moves.encodeMAS(move, alpha);       
                return MAS;
            }
        }
        //MAS has already stored the alpha above in the for loop along with its corresponding move
        MAS = Moves.encodeMAS(move, alpha);
        return MAS;
    }

    static int[] minAlphaBeta(int alpha, int beta, int depth, boolean whitesMove, int move) {
        int[] MAS = new int[2];
        int score;
        int[] moves;   
        
        if(whitesMove) { //if it's whites turn get white moves
            moves = Moves.possibleMovesWhite(whitesMove);    
        } else {
            moves = Moves.possibleMovesBlack(whitesMove);    
        }
        
        if(depth == MAX_DEPTH || moves.length == 0) {
            MAS = Moves.encodeMAS(move, Rating.evaluate(moves, whitesMove, depth));
            return MAS;
        }

        for(int i = 0; i < moves.length; i++) {
            Moves.makeMoveAllPieces(moves[i], false); // I have to make a move in order to get the score
            MAS = maxAlphaBeta(alpha, beta, depth+1, !whitesMove, moves[i]);
            score = Moves.decodeMASScore(MAS);
            Moves.undoMoveAllPieces();
            
            if(score <= beta) {
                beta = score;
                if(depth == 0) {
                    move = Moves.decodeMASMove(MAS);
                }
                MAS = Moves.encodeMAS(move, beta);
            }
            if(alpha >= beta) {
                beta = score;
                MAS = Moves.encodeMAS(move, beta);
                return MAS;               
            }
        }
        //MAS has already stored the beta above in the for loop along with its corresponding move
        MAS = Moves.encodeMAS(move, beta);
        return MAS;
    }
}
/**
 * Test code to verify alphabeta
 **/
    /*
    static Scanner reader = new Scanner(System.in);
 
    static int maxTest(int alpha, int beta, int depth, boolean whitesMove, int move) {
        int score;
        
        if(depth == MAX_DEPTH) {
            System.out.println("Enter a value: ");
            int n = reader.nextInt();
            return n;
        }

        for(int i = 0; i < Moves.MAX_MOVES; i++) {
            if(moves[i] != 0) {
                
                score = minAlphaBeta(alpha, beta, depth+1, !whitesMove, moves[i]);
                
                if(score >= beta) {
                    return beta;
                }
                if(score > alpha) {
                    alpha = score;
                }
            } else {
                break;
            }
        }
        //MAS has already stored the alpha above in the for loop along with its corresponding move
        return ;
    }

    static int minTest(int alpha, int beta, int depth, boolean whitesMove, int move) {
        int score;
        
        if(depth == MAX_DEPTH) {
            System.out.println("Enter a value: ");
            int n = reader.nextInt();
            return n;
        }

        for(int i = 0; i < Moves.MAX_MOVES; i++) {
            if(moves[i] != 0) {
                
                score = maxAlphaBeta(alpha, beta, depth+1, !whitesMove, moves[i]);
                
                if(score <= alpha) {
                    return score;
                }
                if(score < beta) {
                    beta = score;
                }
            } else {
                break;
            }
        }
        return score;
    }*/
