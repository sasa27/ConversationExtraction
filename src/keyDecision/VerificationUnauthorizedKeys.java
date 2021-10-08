package keyDecision;
import java.util.*;

public class VerificationUnauthorizedKeys {
	
	/**
	* Verify that there is no unauthorizedKeys between two KeysFound
	* @param  toVerifyFirst first KeysFound
	* @param  toVerifySecond Second KeysFound
	* @return void
	*/
	
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
