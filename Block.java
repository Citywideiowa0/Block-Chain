package blockChainProgram;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;


public class Block {
	
	int num;
	int data;
	Hash prevHash;
	long nonce;
	Hash currHash;

	public Block() {
		// TODO Auto-generated constructor stub
	}

	public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
		this.num = num;
		this.data = amount;
		this.prevHash = prevHash;
		
		//Create Nonce
		Hash possHash;
		long nonceVal = 0;
		MessageDigest md;
		do {
			nonceVal++;
			ByteBuffer buffy = ByteBuffer.allocate(4).putInt(this.num).putInt(this.data);
			if (this.prevHash != null) {
				buffy.put(this.prevHash.getData());
			}
			buffy.putLong(nonceVal).array();

			md = MessageDigest.getInstance("sha-256");
			md.update(buffy);
			byte[] tempHash = md.digest();
			possHash = new Hash(tempHash);
		}while (! (possHash.getData()[0] == (byte) 0 && possHash.getData()[1] == (byte) 0 && possHash.getData()[2] == (byte) 0));
		
		this.currHash = possHash;
		this.nonce = nonceVal;
	}//Block(int num, int amount, Hash prevHash)
	
	public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
		this.num = num;
		this.data = amount;
		this.prevHash = prevHash;
		this.nonce = nonce;

		// Generate Hash
		// first create byte array
		ByteBuffer buffy = ByteBuffer.allocate(4).putInt(this.num).putInt(this.data);
		if (this.prevHash != null) {
			buffy.put(this.prevHash.getData());
		}
		buffy.putLong(this.nonce).array();

		MessageDigest md = MessageDigest.getInstance("sha-256");
		md.update(buffy);
		byte[] hash = md.digest();
		this.currHash = new Hash(hash);
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
		return this.getHash();
	}
	
	public String toString() {
		return String.format("Block %d (Amount: %d, Nonce: %lf, prevHash: %s, hash: %s", this.getNum(), this.getAmount(), this.getPrevHash().toString(), this.getHash().toString());
	}
}





