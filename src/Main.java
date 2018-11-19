import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Board board = new Board("kalp");
        board.getBoardInfo();
        Leaderboard leaderboard1 = new Leaderboard();
        Leaderboard leaderboard2 = new Leaderboard();
        Leaderboard leaderboard3 = new Leaderboard();
        leaderboard1.getLeaderboard(1);
        leaderboard2.getLeaderboard(2);
        leaderboard3.getLeaderboard(3);
        Game game = new Game();
        game.setBoard("kalp");
        game.setUsername("ww");
        game.updateLeaderboard(12);
        System.out.println(game.isGameOver());

    }
}
