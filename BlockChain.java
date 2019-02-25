package blockChainProgram;

import java.security.NoSuchAlgorithmException;

public class BlockChain {
	
	
	Node<Block> first;
	Node<Block> last;
	

	public BlockChain(int initial) throws NoSuchAlgorithmException {
		this.last = null;
		this.first = new Node<Block>(new Block(0, initial, null), this.last);
	 //this.first.value = new Block(0, initial, null);
	 //this.last.value = this.first.value;
	}

	public Block mine(int amount) throws NoSuchAlgorithmException {
	Block newBlock = new Block(this.last.value.getNum(), this.last.value.getAmount(),this.last.value.getPrevHash());
	return newBlock;
	}
	
	public int getSize() {
		return this.last.value.getNum();
	}
	
	public void append (Block blk) {
		
	}
	
	public boolean removeLast() {
		
	}
	
	public Hash getHash() {
		
	}
	
	public boolean isValidBlockChain() {
		
	}
	
	public void pringBlances() {
		
	}
	
	public String toString() {
		
	}
	
	
	
	
	
	
	// add citation from linear structure lab
	public static class Node<T> {
		/**
		 * The value stored in the ndoe.
		 */
		T value;

		/**
		 * The next node.
		 */
		Node<T> next;

		/**
		 * Create a new node that contains val and that links to next.
		 */
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		} // Node(T, Node<T>)
	} // class Node<T>
	
}//class BlockChain



