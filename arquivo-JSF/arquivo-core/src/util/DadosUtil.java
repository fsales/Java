package util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class DadosUtil implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6715078910776906684L;


	/***
	 * 
	 * @param método que recebe um byte e carrega um InputStream.
	 * @return InputStream
	 * @throws IOException
	 */
	public InputStream criarFileInputStream(byte[] data) {
		return new ByteArrayInputStream(data);
	}
	
	
	/***
	 * 
	 * @param método que recebe um file e carrega somente os arquivo.
	 * @return byte
	 * @throws IOException
	 */
	public byte[] carregarBytes(File file) throws IOException {

		InputStream in = null;
		byte[] out = new byte[0];
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			int bufLen = 20000 * 1024;
			byte[] buf = new byte[bufLen];
			byte[] tmp = null;
			int len = 0;
			while ((len = in.read(buf, 0, bufLen)) != -1) {
				tmp = new byte[out.length + len];
				System.arraycopy(out, 0, tmp, 0, out.length);
				System.arraycopy(buf, 0, tmp, out.length, len);
				out = tmp;
				tmp = null;
			}
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (Exception e) {
				}
		}
		return out;

	}
}
