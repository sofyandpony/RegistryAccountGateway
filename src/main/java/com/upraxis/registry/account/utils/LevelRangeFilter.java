/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upraxis.registry.account.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 *
 * @author Jes
 */
public class LevelRangeFilter extends Filter<ILoggingEvent> {

    private Level levelMax;
    private Level levelMin;

    @Override
    public FilterReply decide(ILoggingEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }

        if (levelMin != null && event.getLevel().levelInt < levelMin.levelInt) {
            return FilterReply.DENY;
        }

        if (levelMax != null && event.getLevel().levelInt > levelMax.levelInt) {
            return FilterReply.DENY;
        }

        return FilterReply.NEUTRAL;
    }

    public Level getLevelMax() {
        return levelMax;
    }

    public void setLevelMax(final Level levelMax) {
        this.levelMax = levelMax;
    }

    public Level getLevelMin() {
        return levelMin;
    }

    public void setLevelMin(final Level levelMin) {
        this.levelMin = levelMin;
    }

    /**
     *
     */
    @Override
    public void start() {
        super.start();
    }

}
