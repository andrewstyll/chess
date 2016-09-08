public class Board {
        static long wPawn = 0l, bPawn = 0l;
        static long wKnight = 0l, bKnight = 0l;
        static long wBishop = 0l, bBishop = 0l;
        static long wRook = 0l, bRook = 0l;
        static long wQueen = 0l, bQueen = 0l;
        static long wKing = 0l, bKing = 0l;
        
        //WHITE IS THE CAPITALISED ONES
        static char charBoard[][] = {
            {'r', 'k', 'b', 'q', 'a', 'k', 'k', 'r'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'K', 'B', 'Q', 'A', 'B', 'K', 'R'},
        };

    public static void initBoard() {
        charBoardToBitBoard();
        //charBoardToBitBoard(charBoard, wPawn, bPawn, wKnight, bKnight, wBishop, bBishop, wRook, bRook, wQueen, bQueen, wKing, bKing);
        System.out.println(wRook);
    }

    //static void charBoardToBitBoard(char[][] charBoard, long wPawn, long bPawn, long wKnight, long bKnight, wBishop, bBishop, wRook, bRook, wQueen, bQueen, wKing, bKing);
    static void charBoardToBitBoard() {
        for(int i = 0; i < 64; i++) {
            switch(charBoard[i/8][i%8]) {
                case 'P':
                    wPawn += powerOf2((i-63)*-1);
                    break;
                case 'p': 
                    bPawn += powerOf2((i-63)*-1);
                    break;
                case 'K': 
                    wKnight += powerOf2((i-63)*-1);
                    break;
                case 'k': 
                    bKnight += powerOf2((i-63)*-1);
                    break;
                case 'B': 
                    wBishop += powerOf2((i-63)*-1);
                    break;
                case 'b': 
                    bBishop += powerOf2((i-63)*-1);
                    break;
                case 'R': 
                    wRook += powerOf2((i-63)*-1);
                    break;
                case 'r': 
                    bRook += powerOf2((i-63)*-1);
                    break;
                case 'Q': 
                    wQueen += powerOf2((i-63)*-1);
                    break;
                case 'q': 
                    bQueen += powerOf2((i-63)*-1);
                    break;
                case 'A': 
                    wKing += powerOf2((i-63)*-1);
                    break;
                case 'a': 
                    bKing += powerOf2((i-63)*-1);
                    break;
            }   
        }
    }

    static int powerOf2(int i) {
        return (1 << i);
    }
}
