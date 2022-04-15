import com.sap.it.api.mapping.*;


	
	def void createIfHasValue (String[] contextValues, Output result){
		
		if (contextValues != null && contextValues.length > 0) {
			for (int i = 0; i < contextValues.length; i++) {
				String value = contextValues[i];
				if (value != null && value.trim().length() > 0
						&& !result.isSuppress(value)) {
					result.addValue("");
				} else {
					result.addSuppress();
				}
			}
		}
	}

	
	def void createIfExistsAndHasValue (String[] contextValues, Output result){
		
		if (contextValues != null && contextValues.length > 0) {
			for (int i = 0; i < contextValues.length; i++) {
				String value = contextValues[i];
				if (value != null && value.trim().length() > 0
						&& !result.isSuppress(value)) {
					result.addValue("");
				} else {
					result.addSuppress();
				}
			}
		} else {
			result.addSuppress();
		}
	}

	
	def void createIfHasOneOfSuchValues (String[] contextValues,  String[] suchValuesString, Output result){
		
		if (suchValuesString == null || suchValuesString.length == 0) {
			throw new IllegalStateException("createIfHasOneOfSuchValues: there is no suchValuesString");
		}
		if (contextValues != null && contextValues.length > 0) {
			String[] suchValues = suchValuesString[0].split(";");

			for (int i = 0; i < contextValues.length; i++) {
				String value = null;
				for (int j = 0; j < suchValues.length; j++) {
					if (suchValues[j].equalsIgnoreCase(contextValues[i])) {
						value = "";
						break;
					}
				}
				result.addValue(value);
			}
		}
	}

	
	def void createIfExistsAndHasOneOfSuchValues (String[] contextValues,  String[] suchValuesString, Output result){
		
		if (suchValuesString == null || suchValuesString.length == 0) {
			throw new IllegalStateException("createIfHasOneOfSuchValues: there is no suchValuesString");
		}
		if (contextValues != null && contextValues.length > 0) {
			String[] suchValues = suchValuesString[0].split(";");

			for (int i = 0; i < contextValues.length; i++) {
				String value = null;
				for (int j = 0; j < suchValues.length; j++) {
					if (suchValues[j].equalsIgnoreCase(contextValues[i])) {
						value = "";
						break;
					}
				}
				result.addValue(value);
			}
		} else {
			result.addSuppress();
		}
	}

	
	def void passIfHasValue (String[] contextValues, Output result){
		
		if (contextValues != null && contextValues.length > 0) {
			for (int i = 0; i < contextValues.length; i++) {
				String str = contextValues[i];
				if (str != null && str.trim().length() > 0) {
					result.addValue(str);
				} else {
					result.addSuppress();
				}
			}
		}
	}

	
	def void passIfExistsAndHasValue (String[] contextValues, Output result){
		
			if (contextValues != null && contextValues.length > 0) {
				for (int i = 0; i < contextValues.length; i++) {
					String str = contextValues[i];
					if (str != null && str.trim().length() > 0) {
						result.addValue(str);
					} else {
						result.addSuppress();
					}
				}
			} else {
				result.addSuppress();
			}
	}

	
	def void assignValueByCondition (String[] conditionContextValues,  String[] suchValuesString,  String[] contextValues, Output result){
		
		// A short form of the standard's IfWithoutElse

		// AbstractTrace trace=container.getTrace();
		if (suchValuesString == null || suchValuesString.length == 0) {
			throw new IllegalStateException("assignValueByCondition: "
					+ "there is no suchValuesString");
		}
		
		// Action when one of the contexts have values
		if (conditionContextValues != null
				&& conditionContextValues.length > 0 || (contextValues != null
				&& contextValues.length > 0)) {
			// Forbidden: both contexts have content but different number of
			// value
			if (contextValues != null && contextValues.length > 0
					&& conditionContextValues != null
					&& conditionContextValues.length > 0
					&& contextValues.length != conditionContextValues.length) {
				throw new IllegalStateException(	"assignValueByCondition: "
								+ "conditionContextValues and contextValues have different lengths");
			}

			if (conditionContextValues == null
					|| conditionContextValues.length == 0
					|| contextValues == null || contextValues.length == 0) {
				// Allowed; one context has content the other hasn't
				result.addSuppress();
			} else {
				String[] conditions = suchValuesString[0].split(";");
				for (int i = 0; i < conditionContextValues.length; i++) {
					String value = null;
					for (int j = 0; j < conditions.length; j++) {
						if (conditions[j]
								.equalsIgnoreCase(conditionContextValues[i])) {
							value = contextValues[i];
							break; // leave the inner loop
						}
					}
					result.addValue(value);
				}
			}
		}
	}

	
	def void simpleUseOneAsManyAndSplitByEachValue (String[] contextValues,  String[] secondContext, Output result){
		
		// AbstractTrace trace=container.getTrace();
		if (secondContext != null && secondContext.length > 0) {
			if (contextValues != null && contextValues.length > 0) {
				String value = null;
				if (contextValues.length == 1) {
					value = contextValues[0];
				} else {
					for (int i = 0; i < contextValues.length; i++) {
						if (!result.isSuppress(contextValues[i])) {
							value = contextValues[i];
							break;
						}
					}
					if (value == null) {
						value = contextValues[0];
					}
				}
				result.addValue(value);
				for (int i = 1; i < secondContext.length; i++) {
					result.addContextChange();
					result.addValue(value);
				}
			} else {
				// bug fix: an empty context must be duplicated too !!
				for (int i = 1; i < secondContext.length; i++) {
					result.addContextChange();
				}
			}
		}
	}

	
	def void simpleUseOneAsMany (String[] contextValues,  String[] secondContext, Output result){
		
		if (contextValues != null && contextValues.length > 0
				&& secondContext != null && secondContext.length > 0) {
			String value = null;
			if (contextValues.length == 1) {
				value = contextValues[0];
			} else {
				for (int i = 0; i < contextValues.length; i++) {
					if (!result.isSuppress(contextValues[i])) {
						value = contextValues[i];
						break;
					}
				}
				if (value == null) {
					value = contextValues[0];
				}
			}
			for (int i = 0; i < secondContext.length; i++) {
				result.addValue(value);
			}
		}
	}

	
	def void deleteSuppress (String[] contextValues, Output result){
		
		if (contextValues != null && contextValues.length > 0) {
			for (int i = 0; i < contextValues.length; i++) {
				if (contextValues[i] != null
						&& !Output.SUPPRESS
								.equalsIgnoreCase(contextValues[i])) {
					result.addValue(contextValues[i]);
				}
			}
		}
	}

	
	def void getFirstContextValue (String[] contextValues, Output result){
		
		if (contextValues != null && contextValues.length > 0) {
			String value = null;
			for (int i = 0; i < contextValues.length; i++) {
				String str = contextValues[i];
				if (str != null && !result.isSuppress(str)) {
					value = str;
					break;
				}
			}
			result.addValue(value);
		}
	}

	
	def void hasValue (String[] contextValues, Output result){
		
		if (contextValues != null && contextValues.length > 0) {
			for (int i = 0; i < contextValues.length; i++) {
				if (contextValues[i] != null
						&& contextValues[i].trim().length() > 0
						&& !Output.SUPPRESS
								.equalsIgnoreCase(contextValues[i])) {
					result.addValue("true");
				} else {
					result.addValue("false");
				}
			}
		}  else {
			result.addValue("false");
		}
	}

	
	def void hasOneOfSuchValues (String[] contextValues,  String[] suchValuesString, Output result){
		
		if (suchValuesString == null || suchValuesString.length == 0) {
			throw new IllegalStateException("hasOneOfSuchValues: "
					+ "there is no suchValuesString");
		}
		
		if (contextValues != null && contextValues.length > 0) {
			String[] suchValues = suchValuesString[0].split(";");

			for (int i = 0; i < contextValues.length; i++) {
				String oneOfSuchValues = "false";
				for (int j = 0; j < suchValues.length; j++) {
					if (suchValues[j].equalsIgnoreCase(contextValues[i])) {
						oneOfSuchValues = "true";
						break;
					}
				}
				result.addValue(oneOfSuchValues);
			}
		}  else {
			result.addValue("false");
		}
	}

	
	def void contextHasOneOfSuchValues (String[] contextValues,  String[] suchValuesString, Output result){
		
		if (contextValues != null && contextValues.length > 0) {
			if (suchValuesString == null || suchValuesString.length == 0
					|| suchValuesString[0] == null) {
				throw new IllegalStateException("contextHasOneOfSuchValues: "
						+ "there is no suchValuesString");
			}
			String[] suchValues = suchValuesString[0].split(";");

			String oneOfSuchValues = "false";
			fcontext: for (int i = 0; i < contextValues.length; i++) {
				for (int j = 0; j < suchValues.length; j++) {
					if (suchValues[j].equalsIgnoreCase(contextValues[i])) {
						oneOfSuchValues = "true";
						break fcontext;
					}
				}
			}

			result.addValue(oneOfSuchValues);
		} else {
			result.addValue("false");
		}
	}

	
	def void useOneContextAsMany (String[] contextValues,  String[] secondContext, Output result){
				if (contextValues != null && contextValues.length > 0) {
			if (secondContext != null && secondContext.length > 0) {
				for (int i = 0; i < secondContext.length - 1; i++) {
					for (int j=0; j < contextValues.length; j++) {
						result.addValue(contextValues[j]);
					}
					result.addContextChange();
				}
				// the last context
				for (int j=0; j < contextValues.length; j++) {
					result.addValue(contextValues[j]);
				}
			}
		}
	}

	
	def void concatToOneQueue (String[] queue1values,  String[] queue2values,  String[] queue3values,  String[] queue4values,  String[] queue5values, Output result){
		
		if (queue1values != null && queue1values.length > 0) {
			for (int i = 0; i < queue1values.length; i++) {
				result.addValue(queue1values[i]);
			}
		} else {
			result.addSuppress();
		}
		if (queue2values != null && queue2values.length > 0) {
			for (int i = 0; i < queue2values.length; i++) {
				result.addValue(queue2values[i]);
			}
		} else {
			result.addSuppress();
		}
		if (queue3values != null && queue3values.length > 0) {
			for (int i = 0; i < queue3values.length; i++) {
				result.addValue(queue3values[i]);
			}
		} else {
			result.addSuppress();
		}
		if (queue4values != null && queue4values.length > 0) {
			for (int i = 0; i < queue4values.length; i++) {
				result.addValue(queue4values[i]);
			}
		} else {
			result.addSuppress();
		}
		if (queue5values != null && queue5values.length > 0) {
			for (int i = 0; i < queue5values.length; i++) {
				result.addValue(queue5values[i]);
			}
		} else {
			result.addSuppress();
		}
	}

	
	def void deleteMultipleContextValues (String[] contextValues, Output result){
		
		List values = new ArrayList();
		if (contextValues != null && contextValues.length > 0) {
			for (int i = 0; i < contextValues.length; i++) {
				if (result.isSuppress(contextValues[i])) {
					continue;
				}
				if (!values.contains(contextValues[i])) {
					values.add(contextValues[i]);
					result.addValue(contextValues[i]);
				}
			}
		}
	}

	
	def void suppressMultipleContextValues (String[] contextValues, Output result){
		
		List values = new ArrayList();
		if (contextValues != null && contextValues.length > 0) {
			for (int i = 0; i < contextValues.length; i++) {
				if (result.isSuppress(contextValues[i])) {
					result.addSuppress();
				} else if (!values.contains(contextValues[i])) {
					values.add(contextValues[i]);
					result.addValue(contextValues[i]);
				} else {
					result.addSuppress();
				}
			}
		}
	}

	
	def void concatContextValues (String[] contextValues,  String[] delimiterString, Output result){
		
		if (delimiterString == null || delimiterString.length == 0) {
			throw new IllegalStateException("concatContextValues: "
					+ "there is no delimiterString");
		}

		if (contextValues != null && contextValues.length > 0) {
			String delimiter = delimiterString[0];
			StringBuffer sb = new StringBuffer(contextValues[0]);
			for (int i = 1; i < contextValues.length; i++) {
				sb.append(delimiter).append(contextValues[i]);
			}

			result.addValue(sb.toString());
		}
	}

	
	def void formatByContextExample (String[] inputQueue,  String[] exampleQueue, Output result){
		
		// format contexts in inputQueue by values (representing contexts) in exampleQueue.
		// this means that contexts can reduce
		// requirement: number of contexts in inputQueue = number of values in exampleQueue
		if (inputQueue != null && inputQueue.length > 0) {
			// declaration
			int inputCCCounter = 1;
			int exampleContextCounter = 0;

			// count number of contexts in inputQueue for checking
			for (int k = 0; k < inputQueue.length; k++) {
				if (Output.CC.equals(inputQueue[k])) {
					inputCCCounter++;
				}
			}

			// count number of values in exampleQueue for checking
			for (int l = 0; l < exampleQueue.length; l++) {
				if (!(Output.CC.equals(exampleQueue[l]))) {
					exampleContextCounter++;
				}
			}

			// start of main computing
			if (inputCCCounter == exampleContextCounter) {
				int inputIndex = 0;

				// looping on exampleQueue
				for (int i = 0; i < exampleQueue.length; i++) {
					// check for context change in exampleQueue
					if (Output.CC.equals(exampleQueue[i])) {
						// add context change to result
						result.addContextChange();
					} else
						// looping on inputQueue
						for (int j = inputIndex; j < inputQueue.length; j++) {
							inputIndex++;
							// check for context changes in inputQueue
							if (Output.CC.equals(inputQueue[j])) {
								break;
							} else {
								// add value to result if there is no context change
								result.addValue(inputQueue[j]);
							}
						}
				}
			} else {
				throw new RuntimeException(	"formatByContextExample: number of contexts in inputQueue ("
								+ inputCCCounter + ") and number of values in exampleQueue ("
								+ exampleContextCounter + ") are not equal.");
			}
		}
	}

	
	def void createMultipleCopies (String[] contextValues,  String[] copyCounts, Output result){
			
	if (contextValues != null && contextValues.length > 0) {
			if (copyCounts == null || contextValues.length != copyCounts.length) {
				throw new IllegalStateException("createMultipleCopies: "
						+ "contextValues and copyCounts have different lengths");
			}
			if (contextValues != null) {
				for (int i = 0; i < contextValues.length; i++) {
					int count = 0;
					try {
						count = Integer.parseInt(copyCounts[i]);
					} catch (Exception ex) {
						throw new RuntimeException("UDF createMultipleCopies: "
								+ copyCounts[i] + " is not a number");
					}
	
					if (count > 0) {
						for (int j = 0; j < count; j++) {
							result.addValue(contextValues[i]);
						}
					} else
						result.addSuppress();
				}
			}
		}
	}

	
	def void createMultipleContextCopies (String[] contextValues,  String[] copyCounts, Output result){
		
		if (copyCounts.length == 0) {
			throw new RuntimeException("UDF createMultipleContextCopies: "
					+ " copyCounts has length 0");
		}
		int count = 0;
		try {
			count = Integer.parseInt(copyCounts[0]);
		} catch (Exception ex) {
			throw new RuntimeException("UDF createMultipleContextCopies: "
					+ copyCounts[0] + " is not a number");
		}
		if (count > 0) {
			for (int i = 0; i < contextValues.length; i++) {
				result.addValue(contextValues[i]);
			}
			for (int i = 1; i < count; i++) {
				result.addContextChange();
				for (int k = 0; k < contextValues.length; k++) {
					result.addValue(contextValues[k]);
				}
			}
		} else {
			for (int i = 0; i < contextValues.length; i++) {
				result.addSuppress();
			}
		}
	}

	
	def void fragmentSingleValue (String[] wholeValue,  String[] maxFragmentCount,  String[] eachFragmentsLength, Output result){
		
		// AbstractTrace trace = container.getTrace();
		if (wholeValue.length == 0) {
			return;
		}
		if (wholeValue.length > 1) {
			List contextValueList = new ArrayList();
			for (int i = 0; i < wholeValue.length; i++) {
				if (!result.isSuppress(wholeValue[i])) {
					contextValueList.add(wholeValue[i]);
				}
			}
			if (contextValueList.size() > 1) {
				throw new RuntimeException("Only one context value is expected");
			}
			if (contextValueList.size() == 0) {
				result.addSuppress();
				return;
			}
		}
		
		String singleValue = wholeValue[0];

		int maxCount;
		int length;
		try {
			maxCount = Integer.parseInt(maxFragmentCount[0]);
		} catch (Exception ex) {
			throw new RuntimeException("UDF fragmentSingleValue: maxFragmentCount"
							+ maxFragmentCount[0] + " is not a number");
		}

		try {
			length = Integer.parseInt(eachFragmentsLength[0]);
		} catch (Exception ex) {
			throw new RuntimeException("UDF fragmentSingleValue: eachFragmentsLength"
							+ eachFragmentsLength[0] + " is not a number");
		}

		int completeLineCount = singleValue.length() / length;
		int rest = singleValue.length() - completeLineCount * length;

		if (completeLineCount == 0 && rest == 0) {
			result.addValue(singleValue);
			return;
		}

		int resultLineCount = Math.min(maxCount, completeLineCount);
		// trace.addInfo("completeLineCount: " + completeLineCount);
		// trace.addInfo("maxCount: " + maxCount);
		// trace.addInfo("resultLineCount: " + resultLineCount);
		// trace.addInfo("rest: " + rest);
		for (int i = 0; i < resultLineCount; i++) {
			String subString = singleValue.substring(i * length, (i + 1)
					* length);
			result.addValue(subString);
		}

		if (maxCount > completeLineCount && rest > 0) {
			String subString = singleValue.substring(completeLineCount
					* length);
			result.addValue(subString);
		}
	}

	
	def void concatTwoQueuesToOne (String[] queue1values, String[] queue2values, Output result){
		
	if (queue1values != null && queue1values.length > 0) {
	    for (int i = 0; i < queue1values.length; i++) {
					result.addValue(queue1values[i]);
	    }
	}
	if (queue2values != null && queue2values.length > 0) {
	    for (int i = 0; i < queue2values.length; i++) {
		result.addValue(queue2values[i]);
	    }
	}
	}

	
	def void rearrangeByKey (String[] arrangedSetOfKeys,  String[] allKeys,  String[] valuesToRearrange, Output result){
		//		AbstractTrace trace = container.getTrace();
		if (allKeys == null || valuesToRearrange == null
				|| allKeys.length != valuesToRearrange.length) {
			throw new IllegalStateException("UDF rearrangeByKey: "
					+ "allKeys and valuesToRearrange have different lengths");
		}

		if (valuesToRearrange == null || valuesToRearrange.length == 0) {
			for (int i = 0; i < arrangedSetOfKeys.length; i++) {
				result.addSuppress();
			}
		} else {
			if (arrangedSetOfKeys == null || arrangedSetOfKeys.length == 0) {
				throw new IllegalStateException(	"UDF rearrangeByKey: there is no arrangedKeySet");
			}

			Set indexSet = new HashSet();
			for (int i = 0; i < allKeys.length; i++) {
				//				trace.addInfo("i: ... " + indexSet);
				//				trace.addInfo("i: ... " + allKeys[i]);
				if (result.isSuppress(allKeys[i])
						|| allKeys[i].trim().length() == 0) {
					// keep suppress values
					result.addSuppress();
				} else {
					// iterate over the arranged key set
					for1 : for (int k = 0; k < arrangedSetOfKeys.length; k++) {
						for (int m = 0; m < allKeys.length; m++) {
							if (indexSet.contains(new Integer(m))) {
								// value is already processed; ignore
								continue;
							}
							if (result.isSuppress(allKeys[m])
									|| allKeys[m].trim().length() == 0) {
								continue;
							}
							if (arrangedSetOfKeys[k].equals(allKeys[m])) {
								// value is not yet already processed; 
								// add tu Output and keep in mind by
								// putting onto a set
								result.addValue(valuesToRearrange[m]);
								indexSet.add(Integer.valueOf(m + ""));
								break for1;
							}
						}
					}
				}
			}
		}
	}

	
	def void getValueByIndex (String[] contextValues,  String[] index, Output result){
				if(contextValues!=null && index!=null){
			String indexStr=index[0];			
			try{
				int indexInt=Integer.parseInt(indexStr);
				
				if(indexInt <= contextValues.length && indexInt >0) result.addValue(contextValues[indexInt-1]);
				return;
			}

			catch(java.lang.NumberFormatException numberFormatExp){
					throw new NumberFormatException(	"UDF getValueByIndex: could not convert index to number");
			}

		}

	}

	
	def void createNumberRange (String[] startValues,  String[] endValues, Output result){
		
		if (startValues.length != endValues.length) {
			throw new IllegalStateException("UDF createNumberRange: "
					+ "startValues and endValues have different lengths");
		}
		for (int i = 0; i < startValues.length; i++) {
			String startValue = startValues[i].trim();
			if (result.isSuppress(startValue)) {
				continue;
			}
			long lv_from;
			try {
				lv_from = Long.parseLong(startValue);
			} catch (NumberFormatException nfe) {
				throw new RuntimeException("UDF createNumberRange: "
						+ " cannot parse long startValue " + startValue);
			}

			String endValue = endValues[i].trim();
			long lv_to;
			if (endValue.length() == 0
					|| result.isSuppress(startValue)) {
				lv_to = lv_from;
			} else {
				try {
					lv_to = Long.parseLong(endValue);
				} catch (NumberFormatException nfe) {
					throw new RuntimeException("UDF createNumberRange: "
							+ " cannot parse long endValue " + endValue);
				}
			}

			for (long k = lv_from; k <= lv_to; k++) {
				result.addValue(k + "");
			}
		}
	}


	
	def void createContextsForFixedBlockSize001 (String[] contextValues,  String[] blockSize, Output result){
				int contextValueLength = contextValues.length;
		if (contextValueLength > 0) {
			int blockSizeInt = 0;
			try {
				blockSizeInt = Integer.parseInt(blockSize[0]);
			} catch (Exception ex) {
				throw new RuntimeException(	"UDF createContextsForFixedBlockSize001 " + blockSize[0] + " isn't an int value");
			}

			int ftxCount = contextValueLength / blockSizeInt;
			if (contextValueLength % blockSizeInt > 0) {
				++ftxCount;
			}
			for (int i = 0; i < ftxCount; i++) {
				result.addValue("");
			}
		} 
	}

	
	def void buildBlocksAndGetValueByIndex001 (String[] contextValues,  String[] indexString,  String[] blockSize, Output result){
				int contextValueLength = contextValues.length;
		if (contextValueLength > 0) {
			int blockSizeInt = 0;
			try {
				blockSizeInt = Integer.parseInt(blockSize[0]);
			} catch (Exception ex) {
				throw new RuntimeException(	"UDF buildBlocksAndGetValueByIndex001 " + blockSize[0] + " isn't an int value");
			}

			int index = 0;
			try {
				index = Integer.parseInt(indexString[0]);
			} catch (Exception ex) {
				throw new RuntimeException("UDF buildBlocksAndGetValueByIndex001 "
						+ indexString[0] + " isn't an index value");
			}
			
			if (index >= blockSizeInt) {
				throw new RuntimeException("UDF buildBlocksAndGetValueByIndex001: indexString "
						+ indexString[0] +  " doesn't fit the blocksize " + blockSize[0]);
			}

			for (int i = index; i < contextValueLength; i += blockSizeInt) {
				result.addValue(contextValues[i]);
			}

			int mod = contextValueLength % blockSizeInt;
			if (mod != 0 && index >= mod) {
				result.addSuppress();
			}
		}
	}

	
	def void splitValueStringToContextValues (String[] inputString,  String[] delimiter, Output result){
			
		if (inputString == null) {
			return;
		}

		for (int i = 0; i < inputString.length; i++) {
			String[] splits = inputString[i].split(delimiter[0]);
			for (int j = 0; j < splits.length; j++) {
				result.addValue(splits[j]);
			}
		}
	}