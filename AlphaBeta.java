class AlphaBeta {
     
    static int MAX_DEPTH = 10;

    //beta = must be at least this good, alpha means too good. Use hard cut
    static int maxAlphaBeta(int alpha, int beta, int depth, boolean whitesMove) {
        int score, moveIndex; //this will be the score of the best move and the index of the best move in the moves array;
        int[] moves = new int[Moves.MAX_MOVES];   

        if(depth == MAX_DEPTH) {
            //return //return the rating here
        }

        if(whitesMove) { //if it's whites turn get white moves
            moves = Moves.possibleMovesWhite(whitesMove);    
        } else {
            moves = Moves.possibleMovesBlack(whitesMove);    
        }

        if(moves[0] == 0) {
            //that means no moves were ever initialised so this must be a checkmate
            //return //return a checkmate rating here;
        }

        for(int i = 0; i < Moves.MAX_MOVES; i++) {
            if(moves[i] != 0) {
                Moves.makeMoveAllPieces(moves[i]); // I have to make a move in order to get the score
                score = maxAlphaBeta(alpha, beta, depth+1, !whitesMove);
                Moves.undoMoveAllPieces();
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
        return alpha;
    }
    static int minAlphaBeta(int alpha, int beta, int depth, boolean whitesMove) {
        int score, moveIndex; //this will be the score of the best move and the index of the best move in the moves array;
        int[] moves = new int[Moves.MAX_MOVES];   
        
        if(depth == MAX_DEPTH) {
            //return //return the rating here
        }

        if(whitesMove) { //if it's whites turn get white moves
            moves = Moves.possibleMovesWhite(whitesMove);    
        } else {
            moves = Moves.possibleMovesBlack(whitesMove);    
        }

        if(moves[0] == 0) {
            //that means no moves were ever initialised so this must be a checkmate
            //return //return a checkmate rating here;
        }

        for(int i = 0; i < Moves.MAX_MOVES; i++) {
            if(moves[i] != 0) {
                Moves.makeMoveAllPieces(moves[i]); // I have to make a move in order to get the score
                score = maxAlphaBeta(alpha, beta, depth+1, !whitesMove);
                Moves.undoMoveAllPieces();
                if(score <= alpha) {
                    return alpha;
                }
                if(score < beta) {
                    beta = score;
                }
            } else {
                break;
            }
        }
        return beta;
    }
}
