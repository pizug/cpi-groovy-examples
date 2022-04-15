import com.sap.it.api.mapping.*;


	
	def String trimZeroLeft (String value){
		
		String output;
		if (value != null) {
			if (value.trim().length() == 0) {
				output = value;
			} else {
				output = value.replaceAll("^0*", "");
				if (output.trim().length() == 0) {
					output = "0";
				}
			}
		} else {
			output = value;
		}

		return output;
	}

	
	def String trimRight (String value){
				
		String result = null;
		int length = value.length();
		if (length > 0) {
			char[] chars = value.toCharArray();
			int trailing = length - 1;			
			while (trailing > -1 && chars[trailing] == ' ') {
				trailing--;
			}
			result = value.substring(0, trailing + 1);
		} else {
			result = value;
		}
		
		return result;
	}

	
	def String fillUpToLengthWithSpace (String value, String length){
		
		String result = value;
		int requiredLength = 0;
		try {
			requiredLength = Integer.parseInt(length);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException("UDF fillUpToLengthWithSpace: the length  " + length
							+ " cannot be transformed into an integer value");
		}

		while (result.length() < requiredLength) {
			result += " ";
		}

		return result;
	}

	
	def String splitToIndex (String value, String index, String delimiter){
		
		String output = null;
		if (value != null) {
			int ind = 0;
			try {
				ind = Integer.parseInt(index);
			} catch (Exception ex) {
				throw new RuntimeException("UDF splitToIndex: index " + index
						+ " is not a numeric value.");
			}
			String[] splits = value.split(delimiter);
			if (splits.length > ind) {
				output = splits[ind];
			} else {
				// output null or suppress may drive the pi crazy
				output = "";
			}
		} else {
			output = value;
		}

		return output;
	}

	
	def String formatValueByZero (String value, String length, String cutLengthDirection, String fillZero, String fillZeroDirection){
		
		//AbstractTrace trace = container.getTrace();
		String output = null;
		if (value != null) {
			int lengthInt;
			try {
				lengthInt = Integer.parseInt(length);
			} catch (Exception ex) {
				throw new RuntimeException("UDF formatValueByZero: length "
						+ length + " is not a numeric value.");
			}
			int lengthValue = value.length();

			if (lengthValue > 0 && lengthValue != lengthInt) {
				if (lengthValue > lengthInt) {
					if ("left".equalsIgnoreCase(cutLengthDirection)) {
						int offset = lengthValue - lengthInt;
						output = value.substring(offset, lengthValue);
						//trace.addInfo("formatValueByZero output  value  " + resultVal);
					} else if ("right".equalsIgnoreCase(cutLengthDirection)) {
						output = value.substring(0, lengthInt);
						//trace.addInfo("formatValueByZero output  value  " + resultVal);
					} else {
						throw new RuntimeException("UDF formatValueByZero: unexpected value "
										+ cutLengthDirection
										+ " for the cutDirection");
					}
				} else {
					if ("true".equalsIgnoreCase(fillZero)) {
						// now lengthValue < lengthInt
						int offset = lengthInt - lengthValue;
						String zeroString = "0";
						for (int i = 0; i < offset - 1; i++) {
							zeroString += "0";
						}
						if ("left".equalsIgnoreCase(fillZeroDirection)) {
							output = zeroString + value;
							//trace.addInfo("formatValueByZero output  value  " + resultVal);
						} else if ("right".equalsIgnoreCase(fillZeroDirection)) {
							output = value + zeroString;
							//trace.addInfo("formatValueByZero output  value  " + resultVal);
						} else {
							throw new RuntimeException("UDF formatValueByZero: unexpected value "
											+ fillZeroDirection
											+ " for the fillDirection");
						}
					}
				}
			}
			if (output == null) {
				output = value;
			}
		} else {
			output = value;
		}

		return output;
	}

	
	def String headString (String value, String headLength){
		
		String output = null;
		if (value != null && headLength != null && headLength.length() > 0) {
			int headLengthInt;
			try {
				headLengthInt = Integer.parseInt(headLength);
			} catch (NumberFormatException numberFormatExp) {
				throw new RuntimeException("UDF headString: could not convert headLength"
								+ headLength + " to integer");
			}

			// DO NOT trim: in some cases the trailing whitespaces may be significant
			int length = value.length();
			if (length > headLengthInt) {
				output = value.substring(0, headLengthInt);
			} else {
				output = value;
			}
		} else {
			output = value;
		}

		return output;
	}

	
	def String tailString (String value, String tailLength){
		
		String output = null;
		if (value != null && tailLength != null
				&& tailLength.trim().length() > 0) {
			int tailLengthInt;
			try {
				tailLengthInt = Integer.parseInt(tailLength);
			} catch (NumberFormatException numberFormatExp) {
				throw new RuntimeException("UDF tailString: could not convert tailLength"
								+ tailLength + " to integer");
			}
			// DO NOT trim as usual: in some cases the trailing whitespaces may be significant
			String trimmedValue = "";
			int valueLength = value.length();
			for (int i = valueLength; i > 0; i--) {
				if (value.charAt(i - 1) != ' ') {
					trimmedValue = value.substring(0, i);
					break;
				}
			}
			
			int length = trimmedValue.length();
			if (length > tailLengthInt) {
				output = trimmedValue.substring(length - tailLengthInt);
			} else {
				output = value;
			}
		} else {
			output = value;
		}

		return output;
	}

	
	def String isNumber (String value){
		
		String output;
		if (value != null) {
			try {
				Double.parseDouble(value.trim());
				output = "true";
			} catch (Exception numExp) {
				output = "false";
			}
		} else {
			output = value;
		}

		return output;
	}

	
	def String toNumber (String numberString){
		
		if (numberString != null && numberString.trim().length() == 0) {
			return numberString;
		}
		try {
			return new BigDecimal(numberString).toString();
		} catch (Exception ex) {
			throw new RuntimeException("UDF toNumber: "
					+ " can not transform numberString " + numberString 
					+ " to a number");
		}
	}

	
	def String isNumberEqualZero (String value){
		
	String output = null;
	if (value != null) {
	    try {
					output = Boolean.toString(Double.parseDouble(value.trim()) == 0.0);
	    } catch (Exception numExp) {
					output = "false";
	    }
	} else {
	    output = value;
	}

	return output;
	}

	
	def String isNumberNotEqualZero (String value){
		
	String output = null;
	if (value != null) {
	    double dd = 0;
	    try {
		      dd = Double.parseDouble(value.trim());
	    } catch (Exception numExp) {
		      output = "false";
	    } finally {
		      if (output == null) {
		          output = Boolean.toString(dd != 0.0);
		      }
	    }
	} else {
	    output = value;
	}

	return output;
	}

	
	def String minusFromBeginToEnd (String value){
		
		String output;
		if (value != null && value.startsWith("-")) {
			output = value.substring(1, value.length()) + "-";
		} else {
			output = value;
		}

		return output;
	}

	
	def String minusFromEndToBegin (String value){
		
		String output;
		if (value != null && value.endsWith("-")) {
			output = "-" + value.substring(0, value.length() - 1);
		} else {
			output = value;
		}
		
		return output;
	}
	
	
def String containsSuchValue (String value, String suchValueString){
    
    return value.contains(suchValueString)
}
	
	
def String padLeft (String value, String padding, int length){
    
    value=value.padLeft(length, padding)
	
	return value	
}

def String padRight (String value, String padding, int length){
    
    value=value.padRight(length, padding)
	
	return value	
}