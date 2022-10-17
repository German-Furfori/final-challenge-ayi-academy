import * as React from 'react';
import Backdrop from '@mui/material/Backdrop';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import Fade from '@mui/material/Fade';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import OutlinedInput from '@mui/material/OutlinedInput';
import Grid from '@mui/material/Grid';
import { updateEmployeeSalary, } from '../../api/employeeApi';

const style = {
  position: 'absolute',
  color: 'white',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  borderRadius: '10px',
  width: 400,
  bgcolor: '#3F3E3E',
  boxShadow: 24,
  p: 4,
};

export default function ModalSalary(props) {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleUpdateSalary = async () => {
    let salary = document.getElementById('salary').value;
    if(salary != null) {
      let response = await updateEmployeeSalary(props.idEmployee, salary);
      alert(response.Message);
      window.location.reload();
    }
  }

  return (
    <div>
      <Button onClick={handleOpen} sx={{ color: '#fff' }}>Update Salary</Button>
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
              Update Salary
            </Typography>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <OutlinedInput
                  placeholder='New salary'
                  type='number'
                  id='salary'
                  fullWidth
                />
               </Grid>
            </Grid>
            <Grid container spacing={1} sx={{paddingTop: 3}}>
              <Grid item xs={6}>
                <Button fullWidth onClick={handleUpdateSalary} sx={{ color: '#fff' }}>
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