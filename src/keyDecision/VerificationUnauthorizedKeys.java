package keyDecision;
import java.util.*;

public class VerificationUnauthorizedKeys {
	public static boolean VerificationUnauthorizedKeysProcedure(KeysFound toVerifyFirst, KeysFound toVerifySecond) {
		for (Set<String> verificationOfAKey : toVerifyFirst.keys) {
			if(toVerifySecond.unauthorizedKeys.contains(verificationOfAKey)){
				return false;
			}
		}
		for (Set<String> verificationOfAKey : toVerifySecond.keys) {
			if(toVerifyFirst.unauthorizedKeys.contains(verificationOfAKey)){
				return false;
			}
		}
		
			return true;
		
	}
}
