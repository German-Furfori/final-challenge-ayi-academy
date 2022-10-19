import * as React from 'react';
import Backdrop from '@mui/material/Backdrop';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import Fade from '@mui/material/Fade';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import OutlinedInput from '@mui/material/OutlinedInput';
import { incrementSalaries } from '../../api/employeeApi';

const style = {
  position: 'absolute',
  color: 'white',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  borderRadius: '10px',
  width: 300,
  bgcolor: '#3F3E3E',
  boxShadow: 24,
  p: 4,
};

export default function ModalSalaries(props) {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleIncrementSalariesButton = async () => {
    let percentage = document.getElementById('percentage').value;
    if(percentage && percentage <= 100 && percentage > 0) {
      let response = await incrementSalaries(percentage);
      alert(response.Message);
      setOpen(false);
    } else {
      alert('Incorrect value');
    }
  }

  return (
    <div>
      <Button onClick={handleOpen} sx={{ color: '#fff', marginBottom: 2, fontSize: 20 }}>Increment Salaries</Button>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        open={open}
        onClose={handleClose}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 500,
        }}
      >
        <Fade in={open}>
          <Box sx={style}>
            <Typography sx={{ paddingBottom: 3 }} id="transition-modal-title" variant="h6" component="h2">
              Increment Salaries
            </Typography>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <OutlinedInput
                  placeholder='Percentage'
                  type='number'
                  id='percentage'
                  fullWidth
                />
              </Grid>
            </Grid>
            <Grid container spacing={1} sx={{paddingTop: 3}}>
              <Grid item xs={6}>
                <Button fullWidth onClick={handleIncrementSalariesButton} sx={{ color: '#fff'}}>
                  Send
                </Button>
              </Grid>
              <Grid item xs={6}>
                <Button fullWidth onClick={handleClose} sx={{ color: '#fff' }}>
                  Cancel
                </Button>
              </Grid>
            </Grid>
          </Box>
        </Fade>
      </Modal>
    </div>
  );
}



// placeholder='Percentage'
//                   name="password"
//                   label="Password"
//                   type='number'
//                   id='percentage'
//                   margin="normal"
//                   required
//                   fullWidth