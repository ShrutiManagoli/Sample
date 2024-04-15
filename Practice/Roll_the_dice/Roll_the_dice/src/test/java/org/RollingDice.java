package org;

import java.util.Scanner;

public class RollingDice {

    public static void main(String args[]){
        int numofstudents =24;
        int nooftests =4;
        Scanner scanner = new Scanner(System.in);
        for(int i=0; i<numofstudents; i++)
        {
            double total =0;
            for(int j=0; j<nooftests; j++)
            {
                System.out.println("Enter the score for subject# " + (j+1));
                double score = scanner.nextDouble();
                total = total + score;
            }
            double average = total/nooftests;
            System.out.println(("Average score for student "+(i+1) +" is "+average));
        }
        scanner.close();
    }
}
