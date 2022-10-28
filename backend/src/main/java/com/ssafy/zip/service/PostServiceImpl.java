package com.ssafy.zip.service;

import com.ssafy.zip.dto.UserDTO;
import com.ssafy.zip.dto.response.PostAllResponseDTO;
import com.ssafy.zip.dto.response.QnaDTO;
import com.ssafy.zip.entity.Qna;
import com.ssafy.zip.entity.QnaLog;
import com.ssafy.zip.repository.BoardRepository;
import com.ssafy.zip.repository.LetterRepository;
import com.ssafy.zip.repository.QnaLogRepository;
import com.ssafy.zip.repository.QnaRepository;
import com.ssafy.zip.util.BoardMapStruct;
import com.ssafy.zip.util.LetterDTOMapStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final QnaRepository qnaRepository;
    private final BoardRepository boardRepository;
    private final QnaLogRepository qnaLogRepository;
    private final LetterRepository letterRepository;

    @Override
    public List<PostAllResponseDTO> getAll(UserDTO userDTO) {

        List<PostAllResponseDTO> result = new ArrayList<>();
        //add board
        result.addAll(boardRepository.findByFamilyId(userDTO.getFamilyId()).stream()
                .map(o-> new PostAllResponseDTO(0,BoardMapStruct.INSTANCE.mapToBoardDTO(o))).toList());
        // add qna
        List<QnaLog> list = qnaLogRepository.findByFamilyId(userDTO.getFamilyId());
        Set<Long> qnaIdSet = new HashSet<>();
        list.forEach(o -> {
            Qna qna = o.getQna();
            if(!qnaIdSet.contains(qna.getId())){
                qnaIdSet.add(qna.getId());
                result.add(new PostAllResponseDTO(1, new QnaDTO(qna.getId(), qna.getQuestion(),o.getReg().toLocalDate().atTime(0, 0),findAnswerCnt(qna.getId(), list))));
            }
        });
        // add letter
        result.addAll(letterRepository.findByFrom_IdOrTo_Id(userDTO.getId(), userDTO.getId())
                .stream().map(o-> new PostAllResponseDTO(2,LetterDTOMapStruct.INSTANCE.mapToLetterRequestDTO(o)))
                .toList());

        result.sort((o1,o2)->o1.data().reg().compareTo(o2.data().reg()));

        return result;
    }

    Integer findAnswerCnt(Long qnaId, List<QnaLog> list){
        return Math.toIntExact(list.stream().filter(o -> o.getQna().getId().equals(qnaId)).count());
    }
}
