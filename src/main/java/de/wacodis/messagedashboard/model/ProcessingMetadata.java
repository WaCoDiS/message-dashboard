package de.wacodis.messagedashboard.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import de.wacodis.messagedashboard.model.AbstractDataEnvelopeAreaOfInterest;
import de.wacodis.messagedashboard.model.AbstractDataEnvelopeTimeFrame;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * extensible datatype for metadata that describes the processing of a product 
 */
@ApiModel(description = "extensible datatype for metadata that describes the processing of a product ")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-11-24T09:01:07.527850200+01:00[Europe/Berlin]")

public class ProcessingMetadata  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("process")
  private String process = null;

  @JsonProperty("areaOfInterest")
  private AbstractDataEnvelopeAreaOfInterest areaOfInterest = null;

  @JsonProperty("timeFrame")
  private AbstractDataEnvelopeTimeFrame timeFrame = null;

  @JsonProperty("created")
  private DateTime created = null;

  public ProcessingMetadata process(String process) {
    this.process = process;
    return this;
  }

  /**
   * name of the process that was responsible for creating the product 
   * @return process
  **/
  @ApiModelProperty(required = true, value = "name of the process that was responsible for creating the product ")
  @NotNull


  public String getProcess() {
    return process;
  }

  public void setProcess(String process) {
    this.process = process;
  }

  public ProcessingMetadata areaOfInterest(AbstractDataEnvelopeAreaOfInterest areaOfInterest) {
    this.areaOfInterest = areaOfInterest;
    return this;
  }

  /**
   * Get areaOfInterest
   * @return areaOfInterest
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public AbstractDataEnvelopeAreaOfInterest getAreaOfInterest() {
    return areaOfInterest;
  }

  public void setAreaOfInterest(AbstractDataEnvelopeAreaOfInterest areaOfInterest) {
    this.areaOfInterest = areaOfInterest;
  }

  public ProcessingMetadata timeFrame(AbstractDataEnvelopeTimeFrame timeFrame) {
    this.timeFrame = timeFrame;
    return this;
  }

  /**
   * Get timeFrame
   * @return timeFrame
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public AbstractDataEnvelopeTimeFrame getTimeFrame() {
    return timeFrame;
  }

  public void setTimeFrame(AbstractDataEnvelopeTimeFrame timeFrame) {
    this.timeFrame = timeFrame;
  }

  public ProcessingMetadata created(DateTime created) {
    this.created = created;
    return this;
  }

  /**
   * time on which the dataset was created 
   * @return created
  **/
  @ApiModelProperty(required = true, value = "time on which the dataset was created ")
  @NotNull

  @Valid

  public DateTime getCreated() {
    return created;
  }

  public void setCreated(DateTime created) {
    this.created = created;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProcessingMetadata processingMetadata = (ProcessingMetadata) o;
    return Objects.equals(this.process, processingMetadata.process) &&
        Objects.equals(this.areaOfInterest, processingMetadata.areaOfInterest) &&
        Objects.equals(this.timeFrame, processingMetadata.timeFrame) &&
        Objects.equals(this.created, processingMetadata.created);
  }

  @Override
  public int hashCode() {
    return Objects.hash(process, areaOfInterest, timeFrame, created);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProcessingMetadata {\n");
    
    sb.append("    process: ").append(toIndentedString(process)).append("\n");
    sb.append("    areaOfInterest: ").append(toIndentedString(areaOfInterest)).append("\n");
    sb.append("    timeFrame: ").append(toIndentedString(timeFrame)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

