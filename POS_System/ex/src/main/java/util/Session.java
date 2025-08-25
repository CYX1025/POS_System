package util;

import model.Employee;
import model.Member;

public class Session {			//紀錄 登錄時是會員還是員工
    private static Member currentMember;
    private static Employee currentEmployee;

    public static void setMember(Member member) {	//會員登錄
        currentMember = member;
        currentEmployee = null; // 確保不會同時有員工和會員
    }

    public static void setEmployee(Employee employee) {	//紙拿來登錄用，讓系統一開始沒有會員資料
        currentEmployee = employee;
        currentMember = null;	
    }
    
    public static void insertMemberData(Member member) { //員工點餐，如果消費者有會員，新增用
    	currentMember = member;
    }

    public static Member getMember() {
        return currentMember;
    }

    public static Employee getEmployee() {
        return currentEmployee;
    }
    
    public static Member Memberisnull() { //店員完成一筆訂單，返回POS使用
		return currentMember = null;
    	
    }

    public static boolean isMemberLogin() {
        return currentMember != null;
    }

    public static boolean isEmployeeLogin() {
        return currentEmployee != null;
    }

    public static void logout() {
        currentMember = null;
        currentEmployee = null;
    }
}
