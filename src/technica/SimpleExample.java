/*
Copyright (c) Microsoft Corporation
All rights reserved. 
MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy of this 
software and associated documentation files (the "Software"), to deal in the Software 
without restriction, including without limitation the rights to use, copy, modify, merge, 
publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons 
to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Software.
THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package technica;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;

public class SimpleExample {

	public static void main(String[] args) throws Exception {

		if (args.length < 2) {
			System.out.println("Usage: SimpleExample <subscription key> <file to transcribe>.");
			return;
		}

		String key = args[0];
		String filepath = args[1];

		SpeechClientREST client = new SpeechClientREST(new Authentication(key));

		InputStream input = new FileInputStream(Paths.get(filepath).toFile());
		chunkInputStream(input, client);
		//byte[] buffer = new byte[100000];
		//while(in.read(buffer) != -1){
			//System.out.println(client.process(input));
			//in = chunkInputStream(input);
		//}
		

	}

	private static void chunkInputStream(InputStream input, SpeechClientREST client) throws IOException, JSONException {
		String output = new String();
		
		byte[] resultBuff = new byte[0];
	    byte[] buff = new byte[2000000];
	    int k = -1;
	    while((k = input.read(buff, 0, buff.length)) > -1) {
	        byte[] tbuff = new byte[resultBuff.length + k]; // temp buffer size = bytes already read + bytes last read
	        System.arraycopy(resultBuff, 0, tbuff, 0, resultBuff.length); // copy previous bytes
	        System.arraycopy(buff, 0, tbuff, resultBuff.length, k);  // copy current lot
	        resultBuff = tbuff; // call the temp buffer as your result buff
	        //System.out.println(client.process(new ByteArrayInputStream(resultBuff)));
	        output += client.process(new ByteArrayInputStream(resultBuff));
	    }
	    //String s = new String(resultBuff);
	    //System.out.println(s + " bytes read.");
	    //System.out.println(new ByteArrayInputStream(resultBuff));
	    //System.out.println(output);
	    JSONObject obj = new JSONObject(output);
	    String n = obj.getString("DisplayText");
	    System.out.println("Generated output: "+n);
	}
}
