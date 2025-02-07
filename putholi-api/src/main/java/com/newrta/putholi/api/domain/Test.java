package com.newrta.putholi.api.domain;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import com.ccavenue.security.AesCryptUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {

	public static void main(String[] args) {
	
		processSucessResponse("9a2048491ff3bc7eb44ef97875a642e3e76f013fdb480219ef9dfa4c32c7f860b4ba067e2776c33765d1f5d0a71f45a53f5eaf59915d623a62a0775288b5b077e543e673336ac9da5e1fc59891b63d9d580b6247e8e656c4072644d6fb63f56fb4d4782d12e2dcb3d2dad7fe370cbdd87425202d486df0bce97d33828d5101196a3e71d7d9fb2598f0da117b73fc58982ac071bb1c130eb7cb85fa3046193c821123f91a9516f55461c94d37df623827a503abc4c8416770ec8182d7646e1bf84338a998dedbef9dc36da5ff693b1e21d3c6355ed3918955129ff4b5718f86c95ce5037e0803704f4c116c9b2765e79a8be5426a0ec8f9a58fb025a77d4331723eccc38e14b189cca56665d6368444a123b6fe69e516a41051bc40524643ab9cb2c80abe6e8b5a7fe3d18c4fb537906fec9a701acd88085ec10be8a7fc69c6a56d9bfdb02333db11d4c1e1fe039facf3873c4cd6232a64233b8edce879a615566af75b11c0486abfa5081a9d374ccd7a8ee0534d5f0e61e065708bfa57bab2fc2d4c48d561b8f340ac713531040082ed0c0a82667be38f2b659002a9031e20178fac752a6a2cbd174171b518087f36432c150f0d290eb540a6fe9099c85a7e0284985ec2f2f08e9187115e09345056741a804b8ebf8eb1cb2a4b0f139d8aa16b9654595bf3372d8d8b42a615b290a4bbe44ae8aca42795806f3ed0e00fd0b8d3431cbe1e330b477ed30198f51c4844978af4a3feda22c3e62de7f7db79f0d45ed24dd6a0d6dc824391a4e259cba00037befea7f18c3b9d29b64d29b180a4f6813ef751222ee8bb0b3727807028b0f688c5aa623ce2b1b5ac4626b438c891f59841c7fb3207a97ebc4c04278496f08fe166c58b51f95ea2f1206ea47f8f73d14d4999ce1e60ac99e116d9aaa843afe92caffc35e41e0511a9ada6464439ffe195af5e77edd6539825a5ee2bc11ac63ee046fccdaeac785ee72808aeca143f851f5e2df83bcf915acbd831e7085420ad0939be8d173b926ec5f005872390f32b54");

	}
	
	public static String processSucessResponse(String encResp) {

		Hashtable hs = getDecryptedValues(encResp);


		String paymentId = hs.get("order_id").toString();

		

		String paramters = "status_message=" + hs.get("status_message").toString() + "&" + "tracking_id="
				+ hs.get("tracking_id").toString() + "&" + "bank_ref_no=" + hs.get("bank_ref_no").toString() + "&"
				+ "amount=" + hs.get("amount").toString() + "&" + "name=" + hs.get("billing_email").toString() + "&"
				+ "order_id=" + paymentId + "&" + "payment_method=" + hs.get("payment_mode").toString() + "&"
				+ "order_status=" + hs.get("order_status") + "&" + "payment_date=" + hs.get("trans_date");

		return Base64.getEncoder().encodeToString(paramters.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * @param encResp
	 */
	private static Hashtable getDecryptedValues(String encResp) {

		AesCryptUtil aesUtil = new AesCryptUtil("3B80A016D6E53AEF2126404E3E4B26B7");
		String decResp = aesUtil.decrypt(encResp);
		StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
		Hashtable hs = new Hashtable();
		String pair = null;
		String pname = null;
		String pvalue = null;
		while (tokenizer.hasMoreTokens()) {
			pair = tokenizer.nextToken();
			if (pair != null) {
				StringTokenizer strTok = new StringTokenizer(pair, "=");
				pname = "";
				pvalue = "";
				if (strTok.hasMoreTokens()) {
					pname = strTok.nextToken();
					if (strTok.hasMoreTokens())
						pvalue = strTok.nextToken();
					hs.put(pname, pvalue);
				}
			}
		}

		Enumeration enumeration = hs.keys();
		while (enumeration.hasMoreElements()) {
//			System.out.print("{} - {}"+ "pname = " + enumeration.nextElement()+ "pvalue = " + hs.get(pname));
			pname = "" + enumeration.nextElement();
			pvalue = "" + hs.get(pname);			
			log.info("{} - {}", pname = "" + pname, pvalue = "" + pvalue);
		}

		return hs;
	}

}
