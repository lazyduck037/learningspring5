package com.taskagile.domain.model.board;

import com.taskagile.domain.model.team.TeamId;
import com.taskagile.domain.model.user.UserId;
import org.springframework.stereotype.Component;

@Component
public class BoardManagement {
    private BoardRepository boardRepository;
    private BoardMemberRepository boardMemberRepository;

    public BoardManagement(BoardRepository boardRepository, BoardMemberRepository boardMemberRepository) {
        this.boardRepository = boardRepository;
        this.boardMemberRepository = boardMemberRepository;
    }

    public Board createBoard(UserId creatorId, String name, String description, TeamId teamId) {
        Board board = Board.create(creatorId, name,
                description, teamId);
        boardRepository.save(board);
        // Add the creator to as a board member
        BoardMember boardMember = BoardMember.create(board.getId(), creatorId);
        boardMemberRepository.save(boardMember);
        return board;
    }
}
