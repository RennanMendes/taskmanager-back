package br.com.abreu.taskmanager.api.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HeaderInfo {
    private String idPessoa;
    private UUID idVenda;
    private Long idQualquer;
}
