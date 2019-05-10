package com.searshc.hs.ec2rebootlambda.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewRelicAlertTarget {
	private String id;
	private String name;
	private String link;
	private String product;
	private String type;
}
