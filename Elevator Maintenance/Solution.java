//****************************************************
//  WATCH OUT FOR UNNECESSARY IMPORTS!!!
//****************************************************
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Solution {

    public static void main(String[] args) {
        String[] input = {"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"};
        solution(input);
    }

    public static String[] solution(String[] input) {
        
        ArrayList<Version> versions = new ArrayList<Version>();

        for (String s : input) {
            versions.add(new Version(s));
        }

        Collections.sort(versions);

        String[] ans = new String[input.length];

        for (int i = 0; i < versions.size(); i++) {
            ans[i] = versions.get(i).get();
        }

        System.out.println(Arrays.toString(ans));

        return ans;
    }

    static class Version implements Comparable<Version> {

        private String version;
    
        public final String get() {
            return this.version;
        }
    
        public Version(String version) {
            this.version = version;
        }
    
        @Override public int compareTo(Version that) {
            if(that == null)
                return 1;
            String[] thisParts = this.get().split("\\.");
            String[] thatParts = that.get().split("\\.");
            int length = Math.max(thisParts.length, thatParts.length);
            for(int i = 0; i < length; i++) {
                int thisPart = i < thisParts.length ?
                    Integer.parseInt(thisParts[i]) : 0;
                int thatPart = i < thatParts.length ?
                    Integer.parseInt(thatParts[i]) : 0;
                if(thisPart < thatPart)
                    return -1;
                if(thisPart > thatPart)
                    return 1;
            }
            return thisParts.length == thatParts.length ? 
                    0 : // If the length of the parts are equal, then the versions are truly equal
                    // Otherwise keep comparing by the length of the parts
                    thisParts.length < thatParts.length ? 
                        -1 : 
                        1;
        }
    
        @Override public boolean equals(Object that) {
            if(this == that)
                return true;
            if(that == null)
                return false;
            if(this.getClass() != that.getClass())
                return false;
            return this.compareTo((Version) that) == 0;
        }
    
    }

    public static int getLongestVersionString(String[] input) {
        int longest = 0;
        for (String s : input) {
            longest = Math.max(longest,s.length());
        }
        return longest;
    }

    public static int[] getVersionNumberArrayFromString(String vString, int numFields) {
        ArrayList<Integer> newList = new ArrayList<Integer>();
        String[] split = vString.split("\\.");
        for (int i = 0; i < numFields; i++) {
            if (i < split.length) {
                newList.add(Integer.parseInt(split[i]));
            }
            else {
                newList.add(0);
            }
        }
        int[] versionNums = convertIntegers(newList);
        System.out.println("\n"+Arrays.toString(versionNums));
        return versionNums;
    }

    public static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }
}