package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quiz")
public class RiskProfileController {

    @PostMapping("/analyze")
    public Map<String, Object> analyze(@RequestBody QuizRequest request) {
        
        // 1. 風險承受力 (Risk Capacity) - 綜合年齡、投資期限與負債
        int ageScore = switch (request.getAgeGroup()) {
            case "under_30" -> 90;
            case "30_50" -> 75;
            case "50_65" -> 50;
            default -> 30;
        };
        int horizonScore = switch (request.getInvestmentHorizon()) {
            case "long" -> 95;
            case "medium_long" -> 80;
            case "medium_short" -> 60;
            default -> 35;
        };
        int debtScore = switch (request.getDebtStatus()) {
            case "none" -> 100;
            case "light" -> 80;
            case "moderate" -> 50;
            default -> 20;
        };
        int riskCapacity = (ageScore + horizonScore + debtScore) / 3;

        // 2. 損失偏好度 (Loss Tolerance) - 心理承擔力
        int lossTolerance = switch (request.getMarketDropReaction()) {
            case "buy_more" -> 95;
            case "hold" -> 70;
            case "sell_part" -> 45;
            default -> 20;
        };

        // 3. 資金流動性 (Liquidity Preference) - 資金彈性與需求
        int liquidityPreference = switch (request.getLiquidityNeed()) {
            case "high" -> 90;   // 高流動性需求
            case "medium" -> 60;
            default -> 30;       // 可接受鎖定資金
        };

        // 4. 資產與能力 (Financial Capability) - 收入穩定度、資產規模與經驗
        int incomeScore = switch (request.getIncomeStatus()) {
            case "very_stable" -> 95;
            case "stable" -> 75;
            case "unstable" -> 50;
            default -> 30;
        };
        int assetScore = switch (request.getAssetScale()) {
            case "very_high" -> 95;
            case "high" -> 80;
            case "medium" -> 60;
            default -> 40;
        };
        int expScore = switch (request.getInvestmentExperience()) {
            case "expert" -> 95;
            case "advanced" -> 80;
            case "intermediate" -> 60;
            default -> 30;
        };
        int financialCapability = (incomeScore + assetScore + expScore) / 3;

        // 產出診斷報告
        String advice;
        int overallScore = (riskCapacity + lossTolerance + financialCapability) / 3;

        if (request.getLiquidityNeed().equals("high") || request.getDebtStatus().equals("heavy")) {
            advice = "【謹慎型 / 保守佈局】您的流動性需求較高或負債壓力較大，建議保留足夠的短期現金與高流動性資產（如貨幣市場基金、短期公債），避免過度投資高波動的風險資產。";
        } else if (overallScore >= 75) {
            advice = "【積極成長型】您的財務狀況穩定、投資期限長且具備良好的風險承受力。建議配置 70%-80% 的股票型 ETF 或成長型股票，搭配 20% 的債券做避險。";
        } else if (overallScore >= 50) {
            advice = "【穩健平衡型】您的風險承受能力中等。建議採取 50% 股票 / 40% 債券 / 10% 現金的資產配置組合，兼顧資產增值與抗跌能力。";
        } else {
            advice = "【防禦保守型】您的風險承受度較低或投資期限偏短。建議以高評級債券、定存與高股息 ETF 為主，穩健累積資產。";
        }

        Map<String, Object> scores = new HashMap<>();
        scores.put("riskCapacity", riskCapacity);
        scores.put("lossTolerance", lossTolerance);
        scores.put("liquidityPreference", liquidityPreference);
        scores.put("financialCapability", financialCapability);

        Map<String, Object> result = new HashMap<>();
        result.put("scores", scores);
        result.put("advice", advice);

        return result;
    }
}