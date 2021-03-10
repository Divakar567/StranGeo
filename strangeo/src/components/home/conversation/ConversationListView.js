import React, { useState, useEffect } from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { makeStyles } from '@material-ui/core/styles';
import { getConversations } from '../../../app/requests';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import { Link } from 'react-router-dom';
import withLoader from '../../utils/hoc/LoaderHOC';
import { ConversationRouter } from '../../../app/routes';

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
    },
    container: {
        maxHeight: '100%',
    },
}));

const columns = [
    { id: 'priority', label: 'Priority', minWidth: '5%' },
    { id: 'subject', label: 'Subject', minWidth: '30%' },
    { id: 'description', label: 'Description', minWidth: '55%' },
    { id: 'status', label: 'Status', minWidth: '10%' },
];

const ConversationListView = props => {
    const { setIsLoading } = props;
    const classes = useStyles();
    const [state, setState] = useState({
        page: 0,
        size: 20,
        from: 0,
        to: 0,
        total: 0,
        conversations: [],
        isError: false,
        errorMessage: ""
    });
    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(10);
    const { keycloak } = useKeycloak();

    useEffect(() => {
        setIsLoading(true);
        getConversations(state.page, state.size, keycloak)
            .then(response => {
                setState({
                    page: state.page,
                    size: state.size,
                    from: state.page * state.size,
                    to: (state.page * state.size) + response.data.numberOfElements,
                    total: response.data.totalElements,
                    conversations: response.data.content,
                });
                setIsLoading(false);
            })
            .catch(error => {
                console.log("Request failed: ", JSON.stringify(error));
                setIsLoading(false);
            });
    }, [state.page, state.size, keycloak, setIsLoading]);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };

    const creatCell = (row, column) => {
        const value = row[column.id];
        const formatedValue = column.format && typeof value === 'number' ? column.format(value) : value;
        return (<TableCell key={column.id} align={column.align}>
            {
                (column.id === "subject") ? (
                    <Link to={"/conversations/" + row.key}>
                        {formatedValue}
                    </Link>
                ) : formatedValue
            }
        </TableCell>);
    }

    let rows = state.conversations.map(conversation => {
        return {
            key: conversation.id,
            priority: conversation.priority,
            subject: conversation.subject,
            description: conversation.description,
            status: conversation.status
        };
    });

    return (
        <React.Fragment>
            <Paper className={classes.root}>
                <TablePagination
                    rowsPerPageOptions={[10, 25, 50, 100]}
                    component="div"
                    rowsPerPage={rowsPerPage}
                    count={state.total}
                    page={page}
                    onChangePage={handleChangePage}
                    onChangeRowsPerPage={handleChangeRowsPerPage}
                />
                <TableContainer className={classes.container}>
                    <Table stickyHeader size="small" aria-label="a sticky dense table">
                        <TableHead>
                            <TableRow>
                                {columns.map((column) => (
                                    <TableCell
                                        key={column.id}
                                        align={column.align}
                                        style={{ minWidth: column.minWidth }}
                                    >
                                        {column.label}
                                    </TableCell>
                                ))}
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {rows.map((row) => {
                                return (
                                    <TableRow hover role="checkbox" tabIndex={-1} key={row.key}>
                                        {columns.map((column) =>
                                            creatCell(row, column)
                                        )}
                                    </TableRow>
                                );
                            })}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Paper>
            <ConversationRouter />
        </React.Fragment>
    );
}

export default withLoader(ConversationListView, "Loading...");