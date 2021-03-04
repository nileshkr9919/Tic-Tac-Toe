import java.io.*;
class TicTacToe{
    public static final int X=1, O=-1;
    public static final int EMPTY=0;
    private int board[][]=new int [3][3];
    private int player;
    public TicTacToe(){
        clearBoard();
    }
    public void clearBoard(){
        for(int i=0; i<3;i++)
            for(int j=0; j<3;j++)
                board[i][j]=EMPTY;
        player=X;
    } 
    public void putMark(int i, int j)throws IllegalArgumentException{
        if((i<0)||(i>2)||(j<0)||(j>2))
            throw new IllegalArgumentException("Invalid position");
        if(board[i][j]!=EMPTY)
            throw new IllegalArgumentException("Position already occupied");
        board[i][j]=player;
        player= -player;
    }
    public boolean isWin(int mark){
        return ((board[0][0]+board[0][1]+board[0][2]==mark*3)
                ||(board[1][0]+board[1][1]+board[1][2]==mark*3)
                ||(board[2][0]+board[2][1]+board[2][2]==mark*3)
                ||(board[0][0]+board[1][0]+board[2][0]==mark*3)
                ||(board[0][1]+board[1][1]+board[2][1]==mark*3)
                ||(board[0][2]+board[1][2]+board[2][2]==mark*3)
                ||(board[0][0]+board[1][1]+board[2][2]==mark*3)
                ||(board[2][0]+board[1][1]+board[0][2]==mark*3));
    }
    public int winner(){
        if(isWin(X))
            return X;
        else if(isWin(O))
            return O;
        else
            return 0;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<3;i++){
            for(int j=0; j<3;j++){
                switch(board[i][j]){
                    case X: sb.append("X");
                            break;
                    case O: sb.append("O");
                            break;
                    case EMPTY: sb.append(" ");
                            break;
                }
                if(j<2)
                    sb.append("|");
            }
            if(i<2)
                sb.append("\n-----\n");
        }
        return sb.toString();
    }
    public static void main(String args[])throws IOException{
        TicTacToe game=new TicTacToe();
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int c=0;
        while(game.winner()==0 && c<=9){
            System.out.println("Enter the position:");
            int a=Integer.parseInt(br.readLine());
            int b=Integer.parseInt(br.readLine());
            game.putMark(a,b);
            System.out.println(game);
            c++;
        }
        String outcome[]={"O wins","Tie","X wins"};
        System.out.println(outcome[1+game.winner()]);
    }
}