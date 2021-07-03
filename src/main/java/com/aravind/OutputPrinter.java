package com.aravind;

/**
 * Printer to help in printing the output back to the user. Output medium can be changed here
 * anytime without affecting any of the other code. Currently, System.out is used. Tomorrow if file
 * has to be used to output, it can be changed here easily.
 */
public class OutputPrinter {

    public void welcome(){
        printWithNewLine("Welcome to AKR parking service");
    }

    public void printWithNewLine(final String msg){
        System.out.println(msg);
    }

    public void parkingLotFull() {
        printWithNewLine("Sorry!! Parking lot is full");
    }

    public void parkingLotEmpty() {
        printWithNewLine("Parking Lot is empty");
    }

    public void statusHeader() {
        printWithNewLine("Slot No.    Registration No    Colour");
    }

    public void notFound() {
        printWithNewLine("Not Found");
    }

    public void end() {
        printWithNewLine("Thanks for using AKR Parking Service");
    }

    public void invalidFile(){
        printWithNewLine("Invalid File Given");
    }
}
