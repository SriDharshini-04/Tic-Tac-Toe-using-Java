import java.util.Scanner;
class TicTacToe{
	static int grid;
	static char[][]board;
	static int count;
	static char player = 'x';
	
	static void play(Scanner sc) {
		int row;
		int col;
		for(int i = 0; i < grid*grid; i++) {
			if(i % 2 == 0)
				System.out.println("Player1 Turn:");
			else
				System.out.println("Player2 Turn:");
			while(true) {
				row = sc.nextInt()-1;
				col = sc.nextInt()-1;
				if(row < 0 || row >= grid || col < 0 || col >= grid) {
					System.out.println("Invalid Position\nChoose Another Position");
					continue;
				}
				if(board[row][col] == 'x' || board[row][col] == 'o') 
					System.out.println("Already This Place is Occupied");
				else {
					board[row][col] = player;
					count ++;
					break;
				}
			}
			display();
			boolean win;
			if(row == col)
				win = diagonalCheck(row,col,player,0,0);
			else if(row+col == grid-1)
				win = diagonalCheck(row,col,player,0,0);
			else
				win = check(row,col,player,0,0);
			if(win) {
				System.out.println((player == 'x' ? "Player 1 won" : "Player 2 won"));
				return;
			}
			if(count == grid*grid) {
				System.out.println("Match Draw");
				return;
			}
			player = switchPlayer(player);
		}
	}
	
	static void display() {
		for(int i = 0; i < grid; i++) {
			for(int j = 0; j < grid; j++) 
				System.out.print(board[i][j]+" | ");
			
			System.out.println();
			System.out.println();
		}
	}
	
	static boolean check(int row, int col, char player, int row_count, int col_count) {
		for(int i = 0; i < grid; i++) {
			if(board[row][i] == player)
				row_count ++;
			if(board[i][col] == player)
				col_count ++;
		}
		if(row_count == grid || col_count == grid)
			return true;
		return false;
	}
	
	static boolean diagonalCheck(int row, int col, char player, int left_count, int right_count) {
		if(row == col) {
			for(int i = 0; i < grid; i++) {
				if(board[i][i] == player)
					left_count ++;
			}
			if(left_count == grid)
				return true;
		}
		if(row+col == grid-1) {
			for(int i = 0; i < grid; i++) {
				if(board[i][grid-i-1] == player)
					right_count ++;
			}
			if(right_count == grid)
				return true;
		}
		return false;
	}
	
	static char switchPlayer(char player) {
		player = (player == 'x') ? 'o' : 'x';
		return player;
	}
	
	public static void main(String ar[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\tTic-Tac-Toe");
		System.out.println("\t\t-----------");
		System.out.println("Enter Row and Column(eg 1 2)");
		System.out.println("Player 1 - x");
		System.out.println("Player 2 - o");
		System.out.println("Let's Start Game");
		System.out.print("Enter Size of the Grid:");
		grid = sc.nextInt();
		board = new char[grid][grid];
		play(sc);
		sc.close();
	}
}