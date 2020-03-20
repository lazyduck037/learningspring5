package com.taskagile.domain.application.impl;

import com.taskagile.domain.application.BoardService;
import com.taskagile.domain.application.commands.CreateBoardCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.model.board.*;
import com.taskagile.domain.model.board.events.BoardCreatedEvent;
import com.taskagile.domain.model.board.events.BoardMemberAddedEvent;
import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UserFinder;
import com.taskagile.domain.model.user.UserId;
import com.taskagile.domain.model.user.UserNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    private BoardRepository boardRepository;
    private BoardManagement boardManagement;
    private DomainEventPublisher domainEventPublisher;
    private BoardMemberRepository boardMemberRepository;
    private UserFinder userFinder;

    public BoardServiceImpl(BoardRepository boardRepository,
                            BoardManagement boardManagement,
                            DomainEventPublisher domainEventPublisher,
                            BoardMemberRepository boardMemberRepository) {
        this.boardRepository = boardRepository;
        this.boardManagement = boardManagement;
        this.domainEventPublisher = domainEventPublisher;
        this.boardMemberRepository = boardMemberRepository;
    }

    @Override
    public List<Board> findBoardsByMembership(UserId userId) {
        return boardRepository.findBoardsByMembership(userId);
    }

    @Override
    public Board createBoard(CreateBoardCommand command) {
        Board board = boardManagement.createBoard(command.getUserId(), command.getName(),
                command.getDescription(), command.getTeamId());
        domainEventPublisher.publish(new BoardCreatedEvent(this, board));
        return board;
    }

    @Override
    public Board findById(BoardId boardId) {
        return boardRepository.findById(boardId);
    }

    @Override
    public List<User> findMembers(BoardId boardId) {
        return boardMemberRepository.findMembers(boardId);
    }

    @Override
    public User addMember(BoardId boardId, String usernameOrEmailAddress) throws UserNotFoundException {
        User user = userFinder.find(usernameOrEmailAddress);
        boardMemberRepository.add(boardId, user.getId());
        domainEventPublisher.publish(new BoardMemberAddedEvent(this, boardId, user));
        return user;
    }
}
