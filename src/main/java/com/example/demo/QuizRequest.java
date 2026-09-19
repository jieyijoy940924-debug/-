package com.example.demo;

public class QuizRequest {
    private String ageGroup;
    private String incomeStatus;
    private String assetScale;
    private String investmentHorizon;
    private String liquidityNeed;
    private String debtStatus;
    private String investmentExperience;
    private String marketDropReaction;

    // Getters and Setters
    public String getAgeGroup() { return ageGroup; }
    public void setAgeGroup(String ageGroup) { this.ageGroup = ageGroup; }

    public String getIncomeStatus() { return incomeStatus; }
    public void setIncomeStatus(String incomeStatus) { this.incomeStatus = incomeStatus; }

    public String getAssetScale() { return assetScale; }
    public void setAssetScale(String assetScale) { this.assetScale = assetScale; }

    public String getInvestmentHorizon() { return investmentHorizon; }
    public void setInvestmentHorizon(String investmentHorizon) { this.investmentHorizon = investmentHorizon; }

    public String getLiquidityNeed() { return liquidityNeed; }
    public void setLiquidityNeed(String liquidityNeed) { this.liquidityNeed = liquidityNeed; }

    public String getDebtStatus() { return debtStatus; }
    public void setDebtStatus(String debtStatus) { this.debtStatus = debtStatus; }

    public String getInvestmentExperience() { return investmentExperience; }
    public void setInvestmentExperience(String investmentExperience) { this.investmentExperience = investmentExperience; }

    public String getMarketDropReaction() { return marketDropReaction; }
    public void setMarketDropReaction(String marketDropReaction) { this.marketDropReaction = marketDropReaction; }
}