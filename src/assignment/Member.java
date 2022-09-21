package assignment;

/**
 *
 * @author Yip Zi Yan
 */

public class Member {

    private String memberID;
    private String name;
    private String contactNo;
    private int point = 0;
    private static int memberCount;

    public Member() {
    }

    public Member(String name, String contactNo) {
        memberCount++;
        memberID = "M" + String.format("%03d", memberCount);
        this.name = name;
        this.contactNo = contactNo;
    }

    public String getMemberID() {
        return memberID;
    }

    public static int getMemberCount() {
        return memberCount;
    }

    public String getName() {
        return name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public int getPoint() {
        return point;
    }

    public static void setMemberCount(int memberCount) {
        Member.memberCount = memberCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean validateMember(String memberID) {
        return this.memberID.equals(memberID);
    }
    
    public void addPoint(int point){
        this.point += point;
    }

    @Override
    public String toString() {
        return String.format("%-6s %-12s %-15s %5d", memberID, name, contactNo, point);
    }

}
