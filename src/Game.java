import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Game {

    private Board gameBoard;
    private Timer gameTime;
    private String username;
    private Leaderboard leaderboard;
    private ArrayList<String> boardlist =  new ArrayList<>();
    public Game(){
        gameBoard = null;
        gameTime = null;
        username = null;
        leaderboard = new Leaderboard();
    }
    public void updateBoardList(){
        File folder = new File("boards");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isDirectory()) {
                boardlist.add(listOfFiles[i].getName());
            }
        }
    }
    public void setBoard( String dir) throws IOException{
        gameBoard = new Board(dir);
        gameBoard.getBoardInfo();
    }
    public boolean isGameOver(){
        Cell[][] allCells =gameBoard.getBoardCells();
        for(int i = 0; i < 20; i++){
            for(int j = 0; j <20; j++){
                if (allCells[i][j].getVisible()){
                    if( !allCells[i][j].getFilled())
                        return false;
                }
            }
        }
        return true;
    }
    public void updateLeaderboard( int score) throws IOException{
        leaderboard.getLeaderboard(gameBoard.getBoardLevel());
        User[] leaderboardArr = leaderboard.getLeaderboardArray();
        User[] newLeaderboard = new User[10];
        for( int i = 0; i < 10; i++)
            newLeaderboard[i] = new User(null,-1);
        int index = 0;
            while ( index < 10) {
                if (score > leaderboardArr[index].getScore()) {
                    newLeaderboard[index].setUsername(username);
                    newLeaderboard[index].setScore(score);

                    while (index < 9 && leaderboardArr[index].getScore() != -1) {
                        newLeaderboard[index + 1].setUsername(leaderboardArr[index].getUsername());
                        newLeaderboard[index + 1].setScore(leaderboardArr[index].getScore());
                        index++;
                    }
                    String path = "leaderboards" + "/" + "leaderboard" + gameBoard.getBoardLevel() + ".txt";
                    File file = new File(path);
                    try {
                        FileWriter fWriter = new FileWriter(file.getAbsoluteFile());
                        BufferedWriter bWriter = new BufferedWriter(fWriter);
                        for (int i = 0; i < 10; i++) {
                            bWriter.write(newLeaderboard[i].getUsername() + "\n" + newLeaderboard[i].getScore() + "\n");
                        }
                        bWriter.close();
                        leaderboard.getLeaderboard(gameBoard.getBoardLevel());
                        return;
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                newLeaderboard[index] = leaderboardArr[index];
                index++;
            }

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getBoardlist() {
        return boardlist;
    }
}
