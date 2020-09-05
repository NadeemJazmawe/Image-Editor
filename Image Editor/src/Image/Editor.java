package Image;
public class Editor {

	/*
	 * this function take three dimensional array , and rotate the image 90 degree right
	 */
	public static int[][][] rotate90(int[][][] im){
		int h = im[0].length;
		int w = im[0][0].length ;
		int [][][] ans = new int [3][w][h];
		for(int i =0 ; i<w ; i++) {
			for(int j =0 ; j<h ; j++) {
				ans[0][i][j] = im[0][h-j-1][w-i-1];
				ans[1][i][j] = im[1][h-j-1][w-i-1];
				ans[2][i][j] = im[2][h-j-1][w-i-1];
			}
		}
		return ans ;
	}
	

	/*
	 * this function take three dimensional array , and smooth it.
	 * the smoot will be calculated by the average of the neighbors(aveNeighbors calculate this average).
	 */
	public static int  [][][] Smooth(int  [][][] mat , int n){
		int w = mat[0].length;
		int h = mat[0][0].length ;
		int [][][] ans = new int [3][w][h];		
		for(int i =0 ; i<w ; i++) {
			for(int j =0 ; j<h ; j++) {
				ans[0][i][j] = (int)aveNeighbors(n , mat , 0 , j , i);
				ans[1][i][j] = (int)aveNeighbors(n , mat , 1 , j , i);
				ans[2][i][j] = (int)aveNeighbors(n , mat , 2 , j , i);
			}
		}
		return ans ;
	}
	
	/*
	 * helper function to Smooth function , it calculate the average of the neighbors.
	 */
	private static int aveNeighbors(int n , int[][][] mat ,int pix ,int w , int h) {
		int starth = h - n , endh = h+n ;
		int startw = w - n , endw = w+n;
		if(starth < 0) 
			starth = 0;
		if(startw < 0)
			startw=0;
		if(endh >= mat[0].length)
			endh = mat[0].length;
		if(endw >= mat[0][0].length)
			endw = mat[0][0].length;

		int sum =mat[pix][h][w] , count = 1;
		for(int i = starth ; i < endh ; i++) {
			for(int j = startw ; j <endw ; j++) {
				sum = sum + mat[pix][i][j];
				count++;
			}
		}
		int ans = (int)(sum/count);
		return ans ;
	}
	
	
	/*
	 * this function take three dimensional array and new height and width.
	 * and it make sacleup to it with the new hight and width.
	 */
	public static int[][] scaleup(double factor_h, double factor_w, int[][] im){
		int [][] ans = new int[(int)(im.length*factor_h)][(int)(im[0].length * factor_w)];

		for (int i = 0; i < im.length; i++) 
			for (int j = 0; j < im[0].length; j++) 
				for (int j2 = 0; j2 < factor_h; j2++) 
					for (int k = 0; k < factor_w; k++) 
						ans[(int)(i*factor_h)+ j2][(int)(j*factor_w) + k] = im[i][j];

		return ans;
	}	


	/*
	 * this function take three dimensional array and return two dimensional array
	 * this function transform the picture from RGB picture to gray picture.
	 */
	public static int[][] rgb2gray(int[][][] im) {
		int w = im[0].length;
		int h = im[0][0].length;
		// we bulid a new array (call answer) , that have return value.
		int[][] answer = new int[w][h];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j <h; j++) {
				/*
				 * here we update the value of the answer array , that it will calculate a new value from the tooken array(im) 
				 * we calculate it in this way : â??(0.3 * R)+ (0.59 * G)+ (0.11 * B)â?‹â‹…255
				 */
				answer[i][j]=(int)((0.3 * im[0][i][j])+ (0.59 * im[1][i][j])+ (0.11* im[2][i][j]))*255;

			}

		}
		return answer;
	}


	/*
	 * this function take three dimensional array and number(equal to 0 or 1 or 2) , and it return three dimensional array
	 * this function transform the picture from RGB picture to one color picture(RED or BLUE or GREEN).
	 */
	public static int[][][] channels(int[][][] im, int n ){
		int w= im[0].length;
		int h= im[0][0].length;
		// we bulid new three dimensional array to put the answer in it and return it 
		int[][][] answer= new int[3][w][h];
		if (n==0 || n==1 || n==2) {
			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					// only the termenal that we choose(according to the number we enter - n) will get in the answer array .
					answer[n][i][j] = im[n][i][j];

				}
			}
		}
		return answer;

	}

	/*
	 * this function take three dimensional array and  return two dimensional array ( 3 x 255)
	 * this function calculate the shades number and enter it in a new array.
	 * after that it will sort our matrix and return it.
	 */
	public static int[][] histogram(int[][][] img){
		int w= img[0].length;
		int h= img[0][0].length;
		int[][] answer= new int [3][256];
		//enter each value to answer matrix.
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				answer[0][img[0][i][j]]++;
				answer[1][img[1][i][j]]++;
				answer[2][img[2][i][j]]++;
			}
		}
		//finally we sort the matrix , i used BubbleSort to sort it.
		BubbleSort(answer[0]);
		BubbleSort(answer[1]);
		BubbleSort(answer[2]);

		return answer;
	}

	public static void BubbleSort(int []array) {
		for (int i = array.length; i >0 ; i--) {
			for (int j = 0; j < i-1; j++) {
				if (array[j]> array[j+1])
					swap(array, j, j+1);
			}
		}
	}

	public static void swap ( int[] arr, int i, int j) {
		int temp= arr[i];
		arr[i]= arr[j];
		arr[j]=temp;

	}
	/*
	 * this function take three dimensional array and number(from 0 to 255) , and it return three dimensional array
	 * the number that we have tooken will be how shades we will enter in the picture.
	 * it calculate any number acording to the number of shades we enter.
	 */
	public static int[][][] pix(int[][][] im, int n){
		int w= im[0].length;
		int h= im[0][0].length;
		int [][][] answer = new int [3][w][h];
		int x = (255/n);
		// calculating the new value to each value in the matrix
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				answer[0][i][j] = im[0][i][j] - (im[0][i][j] % x) ;
				answer[1][i][j] = im[1][i][j] - (im[1][i][j] % x) ;
				answer[2][i][j] = im[2][i][j] - (im[2][i][j] % x) ;
			}
		}

		return answer;
	}

}
