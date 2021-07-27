package keyDecision;
import java.util.*;

public class VerificationUnauthorizedKeys {
	public static boolean VerificationUnauthorizedKeysProcedure(KeysFound toVerifyFirst, KeysFound toVerifySecond) {
		for (Set<String> verificationOfAKey : toVerifyFirst.keys) {
			for(String verificationOfAnAssignment :verificationOfAKey ) {
				if(toVerifySecond.unauthorizedKeys.contains(verificationOfAnAssignment)){
					return false;
				}
			}
		}
		for (Set<String> verificationOfAKey : toVerifySecond.keys) {
			for(String verificationOfAnAssignment :verificationOfAKey ) {
				if(toVerifyFirst.unauthorizedKeys.contains(verificationOfAnAssignment)){
					return false;
				}
			}
		}
		
			return true;
		
	}
}
