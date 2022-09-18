package assignment;

/**
 *
 * @author Yip Zi Yan
 */
import java.time.LocalDate;

public class Member {

    private String memberID;
    private String name;
    private String contactNo;
    private LocalDate birthday;
    private int point = 0;
    private static int memberCount = 1000;

    public Member() {
    }

    public Member(String name, String contactNo, LocalDate birthday) {
        memberCount++;
        memberID = "M" + String.format("%03d", memberCount);
        this.name = name;
        this.contactNo = contactNo;
        this.birthday = birthday;
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

    public LocalDate getBirthday() {
        return birthday;
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

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean validateMember(String memberID) {
        return this.memberID.equals(memberID);
    }

    @Override
    public String toString() {
        return String.format("%-6s %-12s %-13s %-11s %5d", memberID, name, contactNo, birthday, point);
    }

}
