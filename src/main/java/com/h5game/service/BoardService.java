package com.h5game.service;

import com.h5game.constant.ResultCode;
import com.h5game.model.FarestMileageBoard;
import com.h5game.model.ScoreBoard;
import com.h5game.model.TotalMileageBoard;
import com.h5game.repository.FarestMileageBoardRepository;
import com.h5game.repository.ScoreBoardRepository;
import com.h5game.repository.TotalMileageBoardRepository;
import com.h5game.result.GenericResult;
import com.h5game.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author qss
 * @date 2018/12/13
 */
@Slf4j
@Service
public class BoardService {
    private class BoardData<BT> {
        public HashMap<String, BT> board = new HashMap<>();
        public List<BT> list = new ArrayList<>();
        public Object locker = new Object();
    }

    @Autowired
    private ScoreBoardRepository _sBR;
    @Autowired
    private FarestMileageBoardRepository _fmBR;
    @Autowired
    private TotalMileageBoardRepository _tmBR;

    private BoardData<ScoreBoard> _sBoard = new BoardData<ScoreBoard>();
    private BoardData<FarestMileageBoard> _fmBoard = new BoardData<FarestMileageBoard>();
    private BoardData<TotalMileageBoard> _tmBoard = new BoardData<TotalMileageBoard>();
    public final int max = 100;

    public void init() {
        List<ScoreBoard> sBoard = _sBR.init();
        for (ScoreBoard board : sBoard) {
            _sBoard.board.put(board.getUsername(), board);
            _sBoard.list.add(board);
        }
        List<FarestMileageBoard> fmBoard = _fmBR.init();
        for (FarestMileageBoard board : fmBoard) {
            _fmBoard.board.put(board.getUsername(), board);
            _fmBoard.list.add(board);
        }
        List<TotalMileageBoard> tmBoard = _tmBR.init();
        for (TotalMileageBoard board : tmBoard) {
            _tmBoard.board.put(board.getUsername(), board);
            _tmBoard.list.add(board);
        }
    }

    public GenericResult updateTotalMileageBoard(TotalMileageBoard board) {
        String username = board.getUsername();
        if (StringUtils.isBlank(username)) {
            return Results.fail(ResultCode.USER_LOGIN_OVERDUE);
        }
        synchronized (_tmBoard.locker) {
            TotalMileageBoard old = _tmBoard.board.get(username);
            long value = board.getValue();
            if (old != null) {
                if (value < old.getValue()) {
                    //有旧的 同时 新的分数小于旧的分数 不做更新
                    return Results.success();
                } else {
                    old.setValue(board.getValue());
                    Collections.sort(_tmBoard.list, new Comparator<TotalMileageBoard>() {
                        public int compare(TotalMileageBoard b1, TotalMileageBoard b2) {
                            return (int) (b2.getValue() - b1.getValue());
                        }
                    });
                }
            } else {
                _tmBoard.board.put(username, board);
                _tmBoard.list.add(board);
                Collections.sort(_tmBoard.list, new Comparator<TotalMileageBoard>() {
                    public int compare(TotalMileageBoard b1, TotalMileageBoard b2) {
                        return (int) (b2.getValue() - b1.getValue());
                    }
                });
                if (_tmBoard.list.size() > max) {
                    _tmBoard.board.remove(_tmBoard.list.get(_tmBoard.list.size() - 1).getUsername());
                    _tmBoard.list.remove(_tmBoard.list.size() - 1);
                }
            }
            return Results.success();
        }
    }

    public GenericResult updateFarestMileageBoard(FarestMileageBoard board) {
        String username = board.getUsername();
        if (StringUtils.isBlank(username)) {
            return Results.fail(ResultCode.USER_LOGIN_OVERDUE);
        }
        synchronized (_fmBoard.locker) {
            FarestMileageBoard old = _fmBoard.board.get(username);
            long value = board.getValue();
            if (old != null) {
                if (value < old.getValue()) {
                    //有旧的 同时 新的分数小于旧的分数 不做更新
                    return Results.success();
                } else {
                    old.setValue(board.getValue());
                    Collections.sort(_fmBoard.list, new Comparator<FarestMileageBoard>() {
                        public int compare(FarestMileageBoard b1, FarestMileageBoard b2) {
                            return (int) (b2.getValue() - b1.getValue());
                        }
                    });
                }
            } else {
                _fmBoard.board.put(username, board);
                _fmBoard.list.add(board);
                Collections.sort(_fmBoard.list, new Comparator<FarestMileageBoard>() {
                    public int compare(FarestMileageBoard b1, FarestMileageBoard b2) {
                        return (int) (b2.getValue() - b1.getValue());
                    }
                });
                if (_fmBoard.list.size() > max) {
                    _fmBoard.board.remove(_fmBoard.list.get(_fmBoard.list.size() - 1).getUsername());
                    _fmBoard.list.remove(_fmBoard.list.size() - 1);
                }
            }
            return Results.success();
        }
    }

    public GenericResult updateScoreBoard(ScoreBoard board) {
        String username = board.getUsername();
        if (StringUtils.isBlank(username)) {
            return Results.fail(ResultCode.USER_LOGIN_OVERDUE);
        }
        synchronized (_sBoard.locker) {
            ScoreBoard old = _sBoard.board.get(username);
            long value = board.getValue();
            if (old != null) {
                if (value < old.getValue()) {
                    //有旧的 同时 新的分数小于旧的分数 不做更新
                    return Results.success();
                } else {
                    old.setValue(board.getValue());
                    Collections.sort(_sBoard.list, new Comparator<ScoreBoard>() {
                        public int compare(ScoreBoard b1, ScoreBoard b2) {
                            return (int) (b2.getValue() - b1.getValue());
                        }
                    });
                }
            } else {
                _sBoard.board.put(username, board);
                _sBoard.list.add(board);
                Collections.sort(_sBoard.list, new Comparator<ScoreBoard>() {
                    public int compare(ScoreBoard b1, ScoreBoard b2) {
                        return (int) (b2.getValue() - b1.getValue());
                    }
                });
                if (_sBoard.list.size() > max) {
                    _sBoard.board.remove(_sBoard.list.get(_sBoard.list.size() - 1).getUsername());
                    _sBoard.list.remove(_sBoard.list.size() - 1);
                }
            }
            return Results.success();
        }
    }

    public GenericResult getTotalMileageBoard() {
        synchronized (_tmBoard.locker) {
            return Results.success(_tmBoard.list);
        }
    }

    public GenericResult getFarestMileageBoard() {
        synchronized (_fmBoard.locker) {
            return Results.success(_fmBoard.list);
        }
    }

    public GenericResult getScoreBoard() {
        synchronized (_sBoard.locker) {
            return Results.success(_sBoard.list);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveBoard() {
        synchronized (_tmBoard.locker) {
            try {
                _tmBR.deleteAll();
                _tmBR.saveAll(_tmBoard.list);
            } catch (Exception e) {
                log.error("updateTotalMileageBoardException", e.getMessage());
                throw e;
            }
        }
        synchronized (_fmBoard.locker) {
            try {
                _fmBR.deleteAll();
                _fmBR.saveAll(_fmBoard.list);
            } catch (Exception e) {
                log.error("updateFarestMileageBoardException", e.getMessage());
                throw e;
            }
        }
        synchronized (_sBoard.locker) {
            try {
                _sBR.deleteAll();
                _sBR.saveAll(_sBoard.list);
            } catch (Exception e) {
                log.error("updateScoreBoardException", e.getMessage());
                throw e;
            }
        }
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void cron10() {
        log.info("saveBoard");
        saveBoard();
    }

    @Scheduled(cron = "0 0 0 ? * MON")
    public void cleanBoard(){
        log.info("cleanBoard");
        synchronized (_tmBoard.locker) {
            try {
                _tmBR.deleteAll();
                _tmBoard.board.clear();
                _tmBoard.list.clear();
            } catch (Exception e) {
                log.error("cleanTotalMileageBoardException", e.getMessage());
                throw e;
            }
        }
        synchronized (_fmBoard.locker) {
            try {
                _fmBR.deleteAll();
                _fmBoard.board.clear();
                _fmBoard.list.clear();
            } catch (Exception e) {
                log.error("cleanFarestMileageBoardException", e.getMessage());
                throw e;
            }
        }
        synchronized (_sBoard.locker) {
            try {
                _sBR.deleteAll();
                _sBoard.board.clear();
                _sBoard.list.clear();
            } catch (Exception e) {
                log.error("cleanScoreMileageBoardException", e.getMessage());
                throw e;
            }
        }
    }
}
