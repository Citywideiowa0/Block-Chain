package blockChainProgram;

import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

public class BlockChain {

	Node<Block> first;
	Node<Block> last;

	public BlockChain(int initial) throws NoSuchAlgorithmException {
		byte[] nullByteArray = new byte[0];
		Hash nullHash = new Hash(nullByteArray);
		this.first = new Node<Block>(new Block(0, initial, nullHash), null);
		this.last = this.first;
	}

	/*
	 * Caution! Does not check that the transfer amount is valid.
	 */
	public Block mine(int amount) throws NoSuchAlgorithmException {
		Block newBlock = new Block(this.last.value.getNum(), this.last.value.getAmount(), this.getHash());
		return newBlock;
	}

	public int getSize() {
		return this.last.value.getNum() + 1;
	}

	public void append(Block blk) throws IllegalArgumentException {
		if (blk.getPrevHash().equals(this.last.value.getHash())) {
			Node<Block> newNode = new Node<Block>(blk, null);
			this.last.next = newNode;
			this.last = newNode;
		} else {
			throw new IllegalArgumentException("cannot append");
		}
	}

	/*
	 * Caution! removeLast() does not clear removed block from memory. Only removes
	 * it from the linked list
	 */
	public boolean removeLast() {
		if (this.getSize() > 1) {
			Node<Block> temp = this.first;
			while (temp.value.getNum() != (this.last.value.getNum() - 1)) {
				temp = temp.next;
			}
			this.last = temp;
			return true;
		} else {
			return false;
		}
	}

	public Hash getHash() {
		return this.last.value.getHash();
	}

	public boolean isValidBlockChain() {
		int sum = 0;
		Node<Block> temp = this.first;
		Hash prevHash = new Hash (new byte[0]);
		byte[] tempByteArray = null;
		boolean result = true;
		while (temp != null  && temp.value.getNum() < this.getSize()) {
			sum += temp.value.getAmount();
			tempByteArray = temp.value.getHash().getData();
			//if 1) sum is negative/invalid || 2) previous hash does not match current hash || 3) hash does not start with three zeroes
			if (sum < 0 || !(temp.value.getPrevHash().equals(prevHash)) || tempByteArray[0] != (byte) 0 || tempByteArray[1] != (byte) 0 || tempByteArray[2] != (byte) 0) {
				result = false;
			}
			prevHash = temp.value.getHash();
			temp = temp.next;
		}
		return result;
	}

	public void printBalances() {
		PrintWriter pen = new PrintWriter(System.out, true);
		Node<Block> temp = this.first;
		int sum = 0;
		while (temp != null  && temp.value.getNum() < this.getSize()) {
			sum += temp.value.getAmount();
			temp = temp.next;
		}
		pen.println("Alice: " + sum + ", Bob: " + (this.first.value.getAmount() - sum));
	}
	
	public void printBalances(PrintWriter pen) {
		Node<Block> temp = this.first;
		int sum = 0;
		while (temp != null  && temp.value.getNum() < this.getSize()) {
			sum += temp.value.getAmount();
			temp= temp.next;
		}
		pen.println("Alice: " + sum + ", Bob: " + (this.first.value.getAmount() - sum));
	}

	public String toString() {
		Node<Block> temp = this.first;
		String str = "";
		while (temp != null && temp.value.getNum() < this.getSize()) {
			str += temp.value.toString();
			str += '\n';
			temp = temp.next;
		}
		return str;
	}

	// pulled from Linear Structures lab
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

}// class BlockChain


