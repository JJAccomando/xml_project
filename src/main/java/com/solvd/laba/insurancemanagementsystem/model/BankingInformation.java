package com.solvd.laba.insurancemanagementsystem.model;

public class BankingInformation {
    private Integer bankingId;
    private String cardNum;
    private String cardType;
    private Addresses billingAddress;
    private Members bankingMember;

    public Integer getBankingId() { return bankingId; }

    public void setBankingId(Integer bankingId) { this.bankingId = bankingId; }

    public String getCardNum() { return cardNum; }

    public void setCardNum(String cardNum) { this.cardNum = cardNum; }

    public String getCardType() { return cardType; }

    public void setCardType(String cardType) { this.cardType = cardType; }

    public Addresses getBillingAddress() { return billingAddress; }

    public void setBillingAddress(Addresses billingAddress) { this.billingAddress = billingAddress; }

    public Members getBankingMember() { return bankingMember; }

    public void setBankingMember(Members bankingMember) { this.bankingMember = bankingMember; }

    @Override
    public boolean equals(Object other) {
        if ((other instanceof BankingInformation) && (bankingId != null)) {
            return bankingId.equals(((BankingInformation) other).bankingId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (bankingId != null) {
            return this.getClass().hashCode() + bankingId.hashCode();
        }
        return super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Banking Information[ID: %d, Card Number: %s, Card Type: %s, Billing Address ID: %d, Member ID: %d]",
                bankingId, cardNum, cardType, billingAddress.getAddressId(), bankingMember.getMemberId());
    }
}
