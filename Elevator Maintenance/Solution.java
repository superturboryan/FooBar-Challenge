/*
Elevator Maintenance
====================

You've been assigned the onerous task of elevator maintenance - ugh! 

It wouldn't be so bad, except that all the elevator documentation has been lying in a disorganized pile at the bottom of a filing cabinet for years, 
and you don't even know what elevator version numbers you'll be working on. 

Elevator versions are represented by a series of numbers, divided up into major, minor and revision integers. 

New versions of an elevator increase the major number, e.g. 1, 2, 3, and so on. 

When new features are added to an elevator without being a complete new version, 
a second number named "minor" can be used to represent those new additions, e.g. 1.0, 1.1, 1.2, etc. 

Small fixes or maintenance work can be represented by a third number named "revision", e.g. 1.1.1, 1.1.2, 1.2.0, and so on. 

The number zero can be used as a major for pre-release versions of elevators, e.g. 0.1, 0.5, 0.9.2, etc 
(Commander Lambda is careful to always beta test her new technology, with her loyal henchmen as subjects!).

Given a list of elevator versions represented as strings, write a function solution(l) 
that returns the same list sorted in ascending order by major, minor, and revision number so that you can identify the current elevator version. 

The versions in list l will always contain major numbers, but minor and revision numbers are optional. 
If the version contains a revision number, then it will also have a minor number.

For example, given the list l as ["1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"], 
the function solution(l) would return the list ["1.0", "1.0.2", "1.0.12", "1.1.2", "1.3.3"]. 

If two or more versions are equivalent but one version contains more numbers than the others, 
then these versions must be sorted ascending based on how many numbers they have, e.g ["1", "1.0", "1.0.0"]. 

The number of elements in the list l will be at least 1 and will not exceed 100.

-- Java cases --
Input:
Solution.solution({"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"})
Output:
    0.1,1.1.1,1.2,1.2.1,1.11,2,2.0,2.0.0
    [0.1, 1.1.1, 1.2, 1.2.1, 1.11, 2, 2.0, 2.0.0]

Input:
Solution.solution({"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"})
Output:
    1.0,1.0.2,1.0.12,1.1.2,1.3.3
*/

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