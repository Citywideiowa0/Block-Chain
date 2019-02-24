package blockChainProgram;

public class Block {
	
	int num;
	int data;
	Hash prevHash;
	long nonce;
	Hash currHash;

	public Block() {
		// TODO Auto-generated constructor stub
	}

	public Block(int num, int amount, Hash prevHash) {
		this.num = num;
		this.data = amount;
		this.prevHash = prevHash;
		this.nonce = 0;
		this.currHash = new Hash(new byte[3]);
	}
	
	public Block(int num, int amount, Hash prevHash, long nonce) {
		this.num = num;
		this.data = amount;
		this.prevHash = prevHash;
		this.nonce = nonce;
		this.currHash = new Hash(new byte[3]);
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
