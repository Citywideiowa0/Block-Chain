package blockChainProgram;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.lang.String;

public class Hash {
	
	byte[] byteArray;

	public Hash(byte[] data){
		this.byteArray = data;
	}

	public byte[] getData() {
		return this.byteArray;
	}
	
	public boolean isValid() {
		return (this.byteArray[0] == (byte) 0 && this.byteArray[1] == (byte) 0 && this.byteArray[2] == (byte) 0);
	}
	
	public String toString() {
		String str = "";
		for (int i = 0; i <  this.byteArray.length; i++) {
			str.concat(String.format("%2d",Byte.toUnsignedInt(this.byteArray[i])));
		}
		return str;
	}
	
	public boolean equals(Object other) {
		if (other instanceof Hash) {
			Hash o = (Hash) other;
			return Arrays.equals(this.getData(), o.getData());
		} else {
			return false;
		}		
	}
}//class Hash

