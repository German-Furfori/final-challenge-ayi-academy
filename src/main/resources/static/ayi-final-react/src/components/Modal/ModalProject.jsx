import * as React from 'react';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Backdrop from '@mui/material/Backdrop';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import Fade from '@mui/material/Fade';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import { assignProjectToEmployee } from '../../api/employeeApi';
import { findAllProjects } from '../../store/slices/project';

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

export default function ModalProject(props) {
  const dispatch = useDispatch();
  const { projectList } = useSelector(state => state.projectList);

  // Modal actions
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  // Select actions
  const [project, setProject] = React.useState(1);

  const handleChange = (event) => {
    setProject(event.target.value);
    console.log(project);
  };

  const handleAssignProject = async () => {
    let response = await assignProjectToEmployee(props.idEmployee, project);
    alert(response.Message);
    window.location.reload();
  }

  useEffect(() => {
    dispatch(findAllProjects());
  }, [dispatch]);

  return (
    <div>
      <Button onClick={handleOpen} sx={{ color: '#fff', fontSize: 20 }}>Assign Project</Button>
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
              Assign Project
            </Typography>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <Select
                  id="idProject"
                  value={project}
                  onChange={handleChange}
                  fullWidth
                >
                  {projectList.map((project) => (
                    <MenuItem value={project.idProject} key={project.idProject}>{project.idProject} - {project.customer}</MenuItem>
                  ))}
                </Select>
              </Grid>
            </Grid>
            <Grid container spacing={1} sx={{paddingTop: 3}}>
              <Grid item xs={6}>
                <Button fullWidth onClick={handleAssignProject} sx={{ color: '#fff' }}>
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