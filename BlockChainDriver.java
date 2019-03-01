package blockChainProgram;

import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.lang.String;

public class BlockChainDriver {

	public BlockChainDriver() {
	}
	
	public static void printBlockChain(PrintWriter pen, BlockChain chain) {
		pen.println(chain.toString());
	}
	
	
	//Credits: "Simple Java for C Programmers" reading from class
	public static void main (String[] args) throws NumberFormatException, NoSuchAlgorithmException {
		//initialize ouput and input
		PrintWriter pen = new PrintWriter(System.out, true);
		Scanner in = new Scanner(System.in);
		//initialize helper variables
		boolean done = false;
		String input = "";
		
		
		
		//initialize BlockChain
		BlockChain chain = new BlockChain(Integer.parseInt(args[0]));
		

	
		while (!done) {
			//print blockChain
			pen.println(chain.toString());
			//prompt for input
			pen.println("Command? ");
			input = in.next();
			
			//run command
			//Mine
			if (input.contentEquals("mine")) {
				pen.println("Amount Transferred? ");
				input = in.next();
				//print transfer amount and mined nonce
				Block newBlock = chain.mine(Integer.parseInt(input));
				pen.println("Amount = " + input + ", nonce = " + newBlock.getNonce() + "Resultant Hash: " + newBlock.getHash().toString());
				//pen.println("Amount = " + input + ", nonce = " + chain.mine(Integer.parseInt(input)).getNonce() + "Resultant Hash: " + );
				pen.flush();
			//Append
			} else if (input.contentEquals("append")) {
				pen.println("Amount Transferred? ");
				input = in.next();
				pen.println("Nonce? ");
				long nonceInput = Long.parseLong(in.next());
				//append new block
				chain.append(new Block(chain.getSize(), Integer.parseInt(input), chain.getHash(), nonceInput));
				pen.flush();
			//Remove
			} else if (input.contentEquals("remove")) {
				chain.removeLast();
				pen.flush();
			//Check
			}else if (input.contentEquals("check")) {
				pen.println("Checking...");
				//print isValidBlockChain results
				if (chain.isValidBlockChain()) {
					pen.println("Chain is valid!");
					pen.flush();
				} else {
				pen.println("Chain is invalid!");
				pen.flush();
				}
			//Report
			}else if (input.contentEquals("report")) {
				chain.printBalances(pen);
				pen.flush();
			//Help
			} else if (input.contentEquals("help")) {
				pen.println("Valid commands:\n" + 
						"    mine: discovers the nonce for a given transaction\n" + 
						"    append: appends a new block onto the end of the chain\n" + 
						"    remove: removes the last block from the end of the chain\n" + 
						"    check: checks that the block chain is valid\n" + 
						"    report: reports the balances of Alice and Bob\n" + 
						"    help: prints this list of commands\n" + 
						"    quit: quits the program");
				pen.flush();
			} else if (input.contentEquals("quit")) {
				done = true;
			}
		}//end while
		in.close();
	
	}//end main

}//end class
