package com.fusionkoding.citizenshqapi.services;

import java.util.List;

import com.fusionkoding.citizenshqapi.dtos.PilotDTO;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import org.springframework.dao.DuplicateKeyException;

@Service
@RequiredArgsConstructor
public class PilotServiceImpl implements PilotService {



  @Override
  public List<PilotDTO> getPilots() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PilotDTO getPilotById(String pilotId) throws NotFoundException {
      // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PilotDTO createPilot(PilotDTO pilotDTO) throws DuplicateKeyException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PilotDTO updatePilot(String pilotId, String userName, String email, String rsiHandle, String rsiProfileImgUrl,
      String lang, String timeZone, Boolean verfied, String verificationCode, String ueeRecordNumber, String fluency,
      String enlistDate, String country) throws NotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deletePilot(String pilotId) throws NotFoundException {
    // TODO Auto-generated method stub

  }
  
}
