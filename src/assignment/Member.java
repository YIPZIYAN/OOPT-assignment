package assignment;

/**
 *
 * @author Yip Zi Yan
 */

import java.time.LocalDate;

public class Member {

    private int memberID = 1000;
    private String name;
    private String contactNo;
    private LocalDate birthday;
    private int point = 0;
    private static int memberCount;

    public Member() {
    }

    public Member(String name, String contactNo, LocalDate birthday) {
        memberCount++;
        memberID += memberCount;
        this.name = name;
        this.contactNo = contactNo;
        this.birthday = birthday;
    }

    public int getMemberID() {
        return memberID;
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

    public void setMemberID(int menberID) {
        this.memberID = menberID;
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

    public boolean validateMember(int memberID) {
        return this.memberID == memberID;
    }

    @Override
    public String toString() {
        return String.format("%-6d %-12s %-13s %-11s %5d", memberID, name, contactNo, birthday, point);
    }

}
