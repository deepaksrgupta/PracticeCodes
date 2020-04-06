public class SpiralMatrix {
    static int[] spiralCopy(int[][] inputMatrix) {
        // your code goes here

        if(inputMatrix.length  == 0)  return new int[]{};

        int totalCol = inputMatrix[0].length;
        int totalRow = inputMatrix.length;


        int[] output = new int[totalCol * totalRow];

        if(inputMatrix.length == 1) {
            System.arraycopy(inputMatrix[0],0,output,0,inputMatrix.length-1);
            return output;
        }


        int startRow = 0;
        int endRow = inputMatrix.length -1;


        int startCol = 0;
        int endCol = inputMatrix[0].length -1;

        int currentIndex = 0;


        while(startRow < endRow) {



            for(int i = startCol; i <= endCol; i++)
                output[currentIndex++] = inputMatrix[startRow][i];

            startRow++;

            for(int i = startRow ; i <= endRow; i++)
                output[currentIndex++] = inputMatrix[i][endCol];

            endCol--;


            for(int i = endCol ; i >=  startCol; i--)
                output[currentIndex++] = inputMatrix[endRow][i];

            endRow--;


            for(int i = endRow ; i >= startRow; i--)
                output[currentIndex++] = inputMatrix[i][startCol];

            startCol++;

        }

      /*   startRow = 2; endRow = 1; startCol = 1; endCol = 2;
     [ [1,    2,   3,  4,    5],
      [6,    7,   8,  9,   10],
      [11,  12,  13,  14,  15],
      [16,  17,  18,  19,  20] ]


      op = 1,2,3,4,5,10,15,20, 19, 18, 17, 16, 11, 6, 7, 8, 9, 14, 13, 12

    */

        return output;

    }
}
