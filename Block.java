package blockChainProgram;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;
import java.lang.String;


public class Block {
	
	int num;
	int data;
	Hash prevHash;
	long nonce;
	Hash currHash;

	public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
		this.num = num;
		this.data = amount;
		this.prevHash = prevHash;
		
		//Create Nonce
		Hash possHash;
		long nonceVal = 0;
		MessageDigest md;
		do {
			//declare MessageDigest
			md = MessageDigest.getInstance("sha-256");
			//increment to next possible nonce value
			nonceVal++;
			//create byte array of block number
			byte[] numByteArray = ByteBuffer.allocate(4).putInt(this.num).array();
			//update MessageDigest for block number
			md.update(numByteArray);
			//create byte array of block data
			byte[] amountByteArray = ByteBuffer.allocate(4).putInt(this.data).array();
			//update MessageDigest with block data
			md.update(amountByteArray);
			//if there is a prevHash, update MessageDigest with it
			if (!this.prevHash.equals(new Hash(new byte[0]))) {
				md.update(this.prevHash.getData());
			}
			//create byte array for nonce value
			byte[] nonceByteArray = ByteBuffer.allocate(8).putLong(nonceVal).array();
			//update MessageDigest with nonce value
			md.update(nonceByteArray);
			//retrieve created hash
			possHash = new Hash(md.digest());
		}while (! (possHash.getData()[0] == (byte) 0 && possHash.getData()[1] == (byte) 0 && possHash.getData()[2] == (byte) 0));
		this.currHash = possHash;
		this.nonce = nonceVal;
	}//Block(int num, int amount, Hash prevHash)
	
	
	/*
	 * Because MessageDigest.digest(); is pseudo random and will not produce the same hash given the same updates two times in a row, we use the given nonce to find a new nonce that will return a valid hash.m
	 */
	public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
		this.num = num;
		this.data = amount;
		this.prevHash = prevHash;
		
		// Generate Hash similar to other constructor
		Hash possHash;
		MessageDigest md; 
		do {
			md = MessageDigest.getInstance("sha-256");
			nonce++;
			byte[] numByteArray = ByteBuffer.allocate(4).putInt(this.num).array();
			md.update(numByteArray);
			byte[] amountByteArray = ByteBuffer.allocate(4).putInt(this.data).array();
			md.update(amountByteArray);
			if (! this.prevHash.equals(new Hash(new byte[0]))) {
				md.update(this.prevHash.getData());
			}
			byte[] nonceByteArray = ByteBuffer.allocate(8).putLong(nonce).array();
			md.update(nonceByteArray);
			possHash = new Hash(md.digest());
		}while (! (possHash.getData()[0] == (byte) 0 && possHash.getData()[1] == (byte) 0 && possHash.getData()[2] == (byte) 0));
		this.currHash = possHash;
		this.nonce = nonce;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public int getAmount() {
		return this.data;
	}
	
	public long getNonce() {
		return this.nonce;
	}
	
	public Hash getPrevHash() {
		return this.prevHash;
	}
	
	public Hash getHash() {
		return this.currHash;
	}
	
	public String toString() {
		return String.format("Block %d (Amount: %d, Nonce: %d, prevHash: %s, hash: %s)", this.getNum(), this.getAmount(), this.getNonce(), this.getPrevHash().toString(), this.getHash().toString());
	}
}





