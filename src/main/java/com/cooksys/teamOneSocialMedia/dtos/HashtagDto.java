package com.cooksys.teamOneSocialMedia.dtos;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HashtagDto {
	
	public String label;
	
	public Timestamp firstUsed;
	
	public Timestamp lastUsed;
}
