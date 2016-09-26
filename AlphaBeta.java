class AlphaBeta {
    
    /*public static String alphaBeta(int depth, int beta, int alpha, String move, int player) {
        String list = possibleMoves();
        if(depth == 0 || list.length() == 0) {
            return move + Rating.rating(list.length(), depth)*(player*2-1);
        }

        list = sortMoves(list); 

        player = 1-player; // player is 0 or 1
        for(int i = 0; i < list.length(); i+=5) {
            makeMove(list.substring(i, i+5));
            flipBoard(); //to make moves for opposing player
            String returnString = alphaBeta(depth-1, beta, alpha, list.substring(i, i+5), player);
            int value = Integer.valueOf(returnString.substring(5));
            flipBoard();
            undoMove(list.substring(i, i+5));
            
            if( player == 0 ) {
                if(value <= beta) {
                    beta = value;
                    if( depth == globalDepth ) {
                        move = returnString.substring(0, 5);
                    }
                }
            } else {
                if(value > alpha) {
                    alpha = value;
                    if( depth == globalDepth ) {
                        move = returnString.substring(0, 5);
                    }
                }
            }
            
            if(alpha >= beta) {
                return (player == 0) ? move+beta : move+alpha;
            }
        }
        return (player == 0) ? move+beta : move+alpha;
    }*/
   
    /*static int doAlphaBeta() {
        int score, moveIndex; //this will be the score of the best move and the index of the best move in the moves array;
        int[] moves = new int[Moves.MAX_MOVES];   

        if(true) {//depth == MAX_DEPTH) {
            //return //return the rating here
        }

        if(true) { //if it's whites turn get white moves
            moves = Moves.possibleMovesWhite();    
        } else {
            moves = Moves.possibleMovesWhite();    
        }

        //now assuming a move can be made (check if there is a legal move to be made next)
        //each board state is different based on the move used to get there. if a move is made that leaves an unsafe
        //board state for its team, delete that move
        
        // 1. grab all possible moves
        // 2. are we at the bottom? if so return the score. 
        // for each move we have grabbed, make the move, flip the board(so the bot thinks it's the other player), and
        // then call the search again. this will happen until we have reached the bottom of the search, and the search
        // will return a move and its score. we will then perform minimax calculations to determine if this is the
        // minimum, or the maximum value.
        return 0; 
    }*/
    
    static int min() {
        return 0; 
    }

    static int max() {
        return 0;
    }
}
