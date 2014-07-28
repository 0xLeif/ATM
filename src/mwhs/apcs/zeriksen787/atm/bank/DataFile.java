package mwhs.apcs.zeriksen787.atm.bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

	public class DataFile {
	    /**
	     * Reads a CSV data file and returns an array of CheckingAccount
	     * @param path file Path of the CSV file
	     * @return pals from the file
	     */
	    public static CheckingAccount[] read(String path){
	        //CheckingAccount[] friends = DataFile.read("data/peeps.csv");
	        //Open the file
	        BufferedReader file;
	        CheckingAccount[] acqs = new CheckingAccount[0];

	        try {
	            file = new BufferedReader(new FileReader(path));
	            //Read till EOF
	            String line;
	            while((line = file.readLine()) != null){
	                //Prase the line that was just read
	                String[] parts = line.split(",");
	                if(parts.length == 6){
	                //Create an CheckingAccount object

	                	CheckingAccount a = new CheckingAccount(new AccountHolder(parts[0],parts[1],Long.parseLong(parts[2])),Long.parseLong(parts[3]),Integer.parseInt(parts[4]),Double.parseDouble(parts[5]));

	                //Add the object to the array
	                // (1) create a new CheckingAccount array with one extra element
	                CheckingAccount[] temp = new CheckingAccount[acqs.length + 1];
	                    // (2) copy all old elements into new
	                    for(int i = 0; i < acqs.length; i++){
	                        temp[i] = acqs[i]; 
	                    }
	                // (3) Assign a new CheckingAccount oject to the last element of the arrray
	                temp[temp.length - 1] = a;
	                // (4) assign mew array's adress to acqs
	                acqs = temp;
	                }
	            }
	            //close the file            
	            file.close();
	        }catch (FileNotFoundException e) {
	        System.err.println("File Not Found");
	    } catch (IOException e) {
	        System.err.println("Input exception");
	    }
	    return acqs;
	}

	    /**
	     * updates the csv file that is specified to it
	     * @param acqs is the array you want to be writen/saved
	     */
		public static void write(CheckingAccount[] acqs) {
			BufferedWriter file;
			
			try {
				file = new BufferedWriter(new FileWriter("data/data.csv"));
				for(int i = 0; i < acqs.length; i++){
					file.write(				
				 acqs[i].getAccountHolder() + "," + acqs[i].getAccountNumber() + "," + acqs[i].getPin() + "," + acqs[i].getBalance() + "\n");
					
				}
				file.write("\n");
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}