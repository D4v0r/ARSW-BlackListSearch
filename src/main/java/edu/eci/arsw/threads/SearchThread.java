package edu.eci.arsw.threads;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchThread extends Thread {

    private String ipaddress;
    private int start, step, blackListAlarmCount;
    HostBlacklistsDataSourceFacade facade;
    private Collection<Integer> findings;
    AtomicInteger chekedListCount, ocurrencesCount;

    public SearchThread(String ipaddress, int start, int step, HostBlacklistsDataSourceFacade facade, AtomicInteger chekedListCount, AtomicInteger ocurrencesCount, int blackListAlarmCount, LinkedList<Integer> blackListOcurrences){
        this.ipaddress = ipaddress;
        this.start = start;
        this.step = step;
        this.facade = facade;
        findings = blackListOcurrences;
        this.chekedListCount = chekedListCount;
        this.ocurrencesCount = ocurrencesCount;
        this.blackListAlarmCount = blackListAlarmCount;
    }

    public Collection<Integer> getFindings() {
        return findings;
    }

    @Override
    public void run() {
        for (int i = start; i < (start+step) && ocurrencesCount.get() < blackListAlarmCount ; i++) {
            if (facade.isInBlackListServer(i, ipaddress)) {
                findings.add(i);
                ocurrencesCount.getAndIncrement();
            };
            chekedListCount.getAndIncrement();
        }
    }
}
