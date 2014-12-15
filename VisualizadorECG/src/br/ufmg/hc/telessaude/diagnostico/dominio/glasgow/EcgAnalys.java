/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.diagnostico.dominio.glasgow;

import br.ufmg.hc.telessaude.diagnostico.dominio.entity.PojoBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author paulo.gomes
 */
@Entity
@Table(name = "\"ECG_ANALYS\"",schema = PojoBase.DB)
public class EcgAnalys {

    @Id
    @Column(name = "id_exame")
    private Integer idExame;
    @Column(name = "heart_rate")
    private Integer heartRate;
    @Column(name = "heart_rate_variability")
    private Integer heartRateVariability;
    @Column(name = "p_frontal_axis")
    private Integer pFrontalAxis;
    @Column(name = "qrs_frontal_axis")
    private Integer qrsFrontalAxis;
    @Column(name = "st_frontal_axis")
    private Integer stFrontalAxis;
    @Column(name = "sinus_average_rr")
    private Integer sinusAverageRR;
    @Column(name = "sinus_rate")
    private Integer sinusRate;
    @Column(name = "std_dev_norm_rr_intervals")
    private Integer stdDevNormRRIntervals;
    @Column(name = "t_frontal_axis")
    private Integer tFrontalAxis;
    @Column(name = "vent_average_rr")
    private Integer ventAverageRR;
    @Column(name = "vent_rate")
    private Integer ventRate;
    // Scalar Mens.
    @Column(name = "flags")
    private Integer flags;
    @Column(name = "lvh_score")
    private Integer lvHscore;
    @Column(name = "lv_strain")
    private Integer lvStrain;
    @Column(name = "overall_pr__intervals")
    private Integer overallPRInterval;
    @Column(name = "overall_qt_intervals")
    private Integer overallQTInterval;
    @Column(name = "overall_st_duration")
    private Integer overallSTDuration;
    @Column(name = "p_terminal_force_in_v1")
    private Integer pTerminalForceInV1;
    @Column(name = "qt_dispersion")
    private Integer qtDispersion;
    @Column(name = "qtc")
    private Integer qtc;
    @Column(name = "t_angles")
    private Integer tAngles;
    // Qtc

    @Column(name = "qtc_bazett")
    private Integer qtcBazett;
    @Column(name = "qtc_framingham")
    private Integer qtcFramingham;
    @Column(name = "qtc_fridericia")
    private Integer qtcFridericia;
    @Column(name = "qtc_hodge")
    private Integer qtcHodge;
    // Overrall
    @Column(name = "overall_t_duration")
    private Integer overallPDuration;
    @Column(name = "overall_p_onset")
    private Integer overallPOnset;
    @Column(name = "overall_p_termination")
    private Integer overallPTermination;
    @Column(name = "overall_p_duration")
    private Integer overallQrsDuration;
    @Column(name = "overall_qrs_onset")
    private Integer overallQrsOnset;
    @Column(name = "overall_qrs_termination")
    private Integer overallQrsTermination;
    @Column(name = "overall_qrs_duration")
    private Integer overallTDuration;
    @Column(name = "overall_t_onset")
    private Integer overallTOnset;
    @Column(name = "overall_t_termination")
    private Integer overallTTermination;
    // flag
    @Column(name = "default_age ")
    private boolean defaultAge;
    @Column(name = "default_gender ")
    private boolean defaultGender;
    @Column(name = "default_race ")
    private boolean defaultRace;
    @Column(name = "inderterminate_qrs_axis ")
    private boolean inderterminateQRSAxis;
    @Column(name = "p_waves ")
    private boolean pWaves;
    // interpretation
    @Column(name = "description")
    private String description;
    @Column(name = "summary_code")
    private Integer summaryCode;

    public Integer getIdExame() {
        return idExame;
    }

    public void setIdExame(Integer idExame) {
        this.idExame = idExame;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getHeartRateVariability() {
        return heartRateVariability;
    }

    public void setHeartRateVariability(Integer heartRateVariability) {
        this.heartRateVariability = heartRateVariability;
    }

    public Integer getpFrontalAxis() {
        return pFrontalAxis;
    }

    public void setpFrontalAxis(Integer pFrontalAxis) {
        this.pFrontalAxis = pFrontalAxis;
    }

    public Integer getQrsFrontalAxis() {
        return qrsFrontalAxis;
    }

    public void setQrsFrontalAxis(Integer qrsFrontalAxis) {
        this.qrsFrontalAxis = qrsFrontalAxis;
    }

    public Integer getStFrontalAxis() {
        return stFrontalAxis;
    }

    public void setStFrontalAxis(Integer stFrontalAxis) {
        this.stFrontalAxis = stFrontalAxis;
    }

    public Integer getSinusAverageRR() {
        return sinusAverageRR;
    }

    public void setSinusAverageRR(Integer sinusAverageRR) {
        this.sinusAverageRR = sinusAverageRR;
    }

    public Integer getSinusRate() {
        return sinusRate;
    }

    public void setSinusRate(Integer sinusRate) {
        this.sinusRate = sinusRate;
    }

    public Integer getStdDevNormRRIntervals() {
        return stdDevNormRRIntervals;
    }

    public void setStdDevNormRRIntervals(Integer stdDevNormRRIntervals) {
        this.stdDevNormRRIntervals = stdDevNormRRIntervals;
    }

    public Integer gettFrontalAxis() {
        return tFrontalAxis;
    }

    public void settFrontalAxis(Integer tFrontalAxis) {
        this.tFrontalAxis = tFrontalAxis;
    }

    public Integer getVentAverageRR() {
        return ventAverageRR;
    }

    public void setVentAverageRR(Integer ventAverageRR) {
        this.ventAverageRR = ventAverageRR;
    }

    public Integer getVentRate() {
        return ventRate;
    }

    public void setVentRate(Integer ventRate) {
        this.ventRate = ventRate;
    }

    public Integer getFlags() {
        return flags;
    }

    public void setFlags(Integer flags) {
        this.flags = flags;
    }

    public Integer getLvHscore() {
        return lvHscore;
    }

    public void setLvHscore(Integer lvHscore) {
        this.lvHscore = lvHscore;
    }

    public Integer getLvStrain() {
        return lvStrain;
    }

    public void setLvStrain(Integer lvStrain) {
        this.lvStrain = lvStrain;
    }

    public Integer getOverallPRInterval() {
        return overallPRInterval;
    }

    public void setOverallPRInterval(Integer overallPRInterval) {
        this.overallPRInterval = overallPRInterval;
    }

    public Integer getOverallQTInterval() {
        return overallQTInterval;
    }

    public void setOverallQTInterval(Integer overallQTInterval) {
        this.overallQTInterval = overallQTInterval;
    }

    public Integer getOverallSTDuration() {
        return overallSTDuration;
    }

    public void setOverallSTDuration(Integer overallSTDuration) {
        this.overallSTDuration = overallSTDuration;
    }

    public Integer getpTerminalForceInV1() {
        return pTerminalForceInV1;
    }

    public void setpTerminalForceInV1(Integer pTerminalForceInV1) {
        this.pTerminalForceInV1 = pTerminalForceInV1;
    }

    public Integer getQtDispersion() {
        return qtDispersion;
    }

    public void setQtDispersion(Integer qtDispersion) {
        this.qtDispersion = qtDispersion;
    }

    public Integer getQtc() {
        return qtc;
    }

    public void setQtc(Integer qtc) {
        this.qtc = qtc;
    }

    public Integer gettAngles() {
        return tAngles;
    }

    public void settAngles(Integer tAngles) {
        this.tAngles = tAngles;
    }

       public Integer getQtcBazett() {
        return qtcBazett;
    }

    public void setQtcBazett(Integer qtcBazett) {
        this.qtcBazett = qtcBazett;
    }

    public Integer getQtcFramingham() {
        return qtcFramingham;
    }

    public void setQtcFramingham(Integer qtcFramingham) {
        this.qtcFramingham = qtcFramingham;
    }

    public Integer getQtcFridericia() {
        return qtcFridericia;
    }

    public void setQtcFridericia(Integer qtcFridericia) {
        this.qtcFridericia = qtcFridericia;
    }

    public Integer getQtcHodge() {
        return qtcHodge;
    }

    public void setQtcHodge(Integer qtcHodge) {
        this.qtcHodge = qtcHodge;
    }

    public Integer getOverallPDuration() {
        return overallPDuration;
    }

    public void setOverallPDuration(Integer overallPDuration) {
        this.overallPDuration = overallPDuration;
    }

    public Integer getOverallPOnset() {
        return overallPOnset;
    }

    public void setOverallPOnset(Integer overallPOnset) {
        this.overallPOnset = overallPOnset;
    }

    public Integer getOverallPTermination() {
        return overallPTermination;
    }

    public void setOverallPTermination(Integer overallPTermination) {
        this.overallPTermination = overallPTermination;
    }

    public Integer getOverallQrsDuration() {
        return overallQrsDuration;
    }

    public void setOverallQrsDuration(Integer overallQrsDuration) {
        this.overallQrsDuration = overallQrsDuration;
    }

    public Integer getOverallQrsOnset() {
        return overallQrsOnset;
    }

    public void setOverallQrsOnset(Integer overallQrsOnset) {
        this.overallQrsOnset = overallQrsOnset;
    }

    public Integer getOverallQrsTermination() {
        return overallQrsTermination;
    }

    public void setOverallQrsTermination(Integer overallQrsTermination) {
        this.overallQrsTermination = overallQrsTermination;
    }

    public Integer getOverallTDuration() {
        return overallTDuration;
    }

    public void setOverallTDuration(Integer overallTDuration) {
        this.overallTDuration = overallTDuration;
    }

    public Integer getOverallTOnset() {
        return overallTOnset;
    }

    public void setOverallTOnset(Integer overallTOnset) {
        this.overallTOnset = overallTOnset;
    }

    public Integer getOverallTTermination() {
        return overallTTermination;
    }

    public void setOverallTTermination(Integer overallTTermination) {
        this.overallTTermination = overallTTermination;
    }

    public boolean isDefaultAge() {
        return defaultAge;
    }

    public void setDefaultAge(boolean defaultAge) {
        this.defaultAge = defaultAge;
    }

    public boolean isDefaultGender() {
        return defaultGender;
    }

    public void setDefaultGender(boolean defaultGender) {
        this.defaultGender = defaultGender;
    }

    public boolean isDefaultRace() {
        return defaultRace;
    }

    public void setDefaultRace(boolean defaultRace) {
        this.defaultRace = defaultRace;
    }

    public boolean isInderterminateQRSAxis() {
        return inderterminateQRSAxis;
    }

    public void setInderterminateQRSAxis(boolean inderterminateQRSAxis) {
        this.inderterminateQRSAxis = inderterminateQRSAxis;
    }

    public boolean ispWaves() {
        return pWaves;
    }

    public void setpWaves(boolean pWaves) {
        this.pWaves = pWaves;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSummaryCode() {
        return summaryCode;
    }

    public void setSummaryCode(Integer summaryCode) {
        this.summaryCode = summaryCode;
    }

}
